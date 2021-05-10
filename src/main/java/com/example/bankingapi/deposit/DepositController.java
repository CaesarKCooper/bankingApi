package com.example.bankingapi.deposit;

import com.example.bankingapi.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositRepo depositRepo;

    private void verifyDeposit(Long depositId) { //Finish This Method
        Optional<Deposit> deposit = depositService.getDepositById(depositId);
        if (deposit.isEmpty()){
        }
    }

    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Deposit>> getAllDeposits(@PathVariable Deposit deposit) {
        depositService.getAllDeposits();
        return new ResponseEntity<>(depositRepo.findAll(), HttpStatus.OK);
    }


    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId) {
        depositService.getDepositById(depositId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.POST)
    public ResponseEntity<?> createDeposit(@PathVariable Account account, @RequestBody Deposit deposit){
        depositService.createDeposit(deposit);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newDepositUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{depositId}").buildAndExpand(deposit.getId()).toUri();
        responseHeaders.setLocation(newDepositUri);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDeposit(Deposit deposit , @PathVariable Long depositId){
        verifyDeposit(depositId);
        depositService.updateDeposit(deposit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable Deposit deposit, Long depositId) {
        verifyDeposit(depositId);
        depositService.deleteDeposit(deposit);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
