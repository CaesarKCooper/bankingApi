package com.example.bankingapi.deposit;
import com.example.bankingapi.account.Account;
import com.example.bankingapi.exceptionhandling.CodeData;
import com.example.bankingapi.exceptionhandling.CodeMessage;
import com.example.bankingapi.exceptionhandling.CodeMessageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;

    @Autowired
    private DepositRepo depositRepo;

    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDepositsByAccountId(@PathVariable Long accountId) {

        Iterable<Deposit> deposits = depositService.getAllDepositsByAccountId(accountId);
        if (deposits.iterator().hasNext()) {
            CodeData response = new CodeData(200, accountId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        CodeMessage exception = new CodeMessage(404, "Account not found" );
         return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepositById(@PathVariable Long depositsId) {
        Optional<Deposit> deposit = depositService.getDepositByDepositId(depositsId);
        if (deposit == null) {
            CodeMessage exception = new CodeMessage(404, "error fetching deposit with id " + depositsId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        CodeData response = new CodeData(200, deposit);
        return new ResponseEntity<>(depositService.getDepositByDepositId(depositsId),HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.POST)
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit){
        if (!depositService.accountCheck(accountId)) {
            CodeMessage exception = new CodeMessage("Error creating withdrawal: Account not found");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        Deposit deposit1 = depositService.createDeposit(deposit, accountId);
        if(deposit1.getAmount() <= 0){
            CodeMessage exception = new CodeMessage("Error creating deposit: Deposit amount must be greater than zero");
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
        CodeMessageData response = new CodeMessageData(201, "Created deposit and added it to the account", deposit1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//        if (depositService.depositCheck(accountId)){
//            CodeMessage response = new CodeMessage(201, "Created deposit and added it to the account");
//            return new ResponseEntity<>(depositService.createDeposit(deposit, accountId), HttpStatus.CREATED);
//        }
//        CodeMessage exception = new CodeMessage(404, "Error creating deposit: Account not found");
//        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
//    }

    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositsId, @RequestBody Deposit deposit){

        if(depositService.depositCheck(depositsId)){
            depositService.updateDeposit(deposit, depositsId);
            CodeMessage exception = new CodeMessage(404, "Deposit ID does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Accepted deposit modification", HttpStatus.OK);
    }

    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositsId) {

        if (depositService.depositCheck(depositsId)){
            depositService.deleteDeposit(depositsId);
            CodeMessage exception = new CodeMessage(404, "This id does not exist in deposits");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        depositService.deleteDeposit(depositsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}