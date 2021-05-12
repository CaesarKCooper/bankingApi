package com.example.bankingapi.withdrawal;


import com.example.bankingapi.bill.Bill;
import com.example.bankingapi.exceptionhandling.CodeData;
import com.example.bankingapi.exceptionhandling.CodeMessage;
import com.example.bankingapi.exceptionhandling.CodeMessageData;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class WithdrawalController {

    @Autowired
    WithdrawalService withdrawalService;

    @Autowired
    WithdrawalRepository withdrawalRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {

       Iterable<Withdrawal> withdrawals =  withdrawalService.getAllWithdrawalsByAccountId(accountId);
        if(withdrawalRepository.getWithdrawalByAccountId(accountId).isEmpty()){

            CodeMessage exception = new CodeMessage("Account not found");
        return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
    }
        CodeData response = new CodeData(200, withdrawals);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.GET)
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){

        Optional<Withdrawal> withdrawal =  withdrawalService.getWithdrawalByWithdrawalId(withdrawalId);
        if(withdrawal == null){
            CodeMessage exception = new CodeMessage("error fetching withdrawal with withdrawal id " + withdrawalId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        CodeData response = new CodeData(200, withdrawal);
        return new ResponseEntity<> (response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal) {
        if (!withdrawalService.accountCheck(accountId)) {
            CodeMessage exception = new CodeMessage("Error creating withdrawal: Account not found");
            return new ResponseEntity<>(exception, HttpStatus.CREATED);
        }
        Withdrawal w1 = withdrawalService.createWithdrawal(withdrawal, accountId);
        CodeMessageData response = new CodeMessageData(201, "Created bill and added it to the account", w1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable Long withdrawalId, @RequestBody Withdrawal withdrawal) {
        if (!withdrawalService.withdrawalCheck(withdrawalId)) {
            CodeMessage exception = new CodeMessage("Withdrawal ID does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        withdrawalService.updateWithdrawal(withdrawal);
        CodeMessage response = new CodeMessage(202, "Accepted withdrawal modification");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){

    if(!withdrawalService.withdrawalCheck(withdrawalId)){
        CodeMessage exception = new CodeMessage("This id does not exist in withdrawals");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

        withdrawalService.deleteWithdrawal(withdrawalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
