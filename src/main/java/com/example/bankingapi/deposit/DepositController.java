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

<<<<<<< HEAD
    private void verifyDeposit(Long depositId) { //Finish This Method
        Optional<Deposit> deposit = depositService.getDepositById(depositId);
        if (deposit.isEmpty()){
        }
    }

    @RequestMapping(value = "/accounts/{accountId}/deposit", method = RequestMethod.GET)
=======
/*
    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.GET)
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
    public ResponseEntity<Iterable<Deposit>> getAllDepositsByAccountId(@PathVariable Long accountId) {

        return new ResponseEntity<>(depositService.getAllDepositsByAccountId(accountId), HttpStatus.OK);
    }
<<<<<<< HEAD


    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId) {

        return new ResponseEntity<>(HttpStatus.OK, depositService.getDepositById(depositId));
=======
*/


    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepositById(@PathVariable Long depositsId) {

        return new ResponseEntity<>(depositService.getDepositById(depositsId),HttpStatus.OK);
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
    }

    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.POST)
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit){
<<<<<<< HEAD
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newDepositUri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{depositId}").buildAndExpand(deposit.getId()).toUri();
//        responseHeaders.setLocation(newDepositUri);
=======

>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b

        return new ResponseEntity<>(depositService.createDeposit(deposit, accountId), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.PUT)
<<<<<<< HEAD
    public ResponseEntity<?> updateDeposit(Deposit deposit , @PathVariable Long depositsId){
        verifyDeposit(depositsId);
        depositService.updateDeposit(deposit);
        return new ResponseEntity<>(HttpStatus.OK);
=======
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositsId, @RequestBody Deposit deposit){

        depositService.updateDeposit(deposit);
        return new ResponseEntity<>("Accepted deposit modification", HttpStatus.OK);
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
    }

    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositsId) {
<<<<<<< HEAD
        verifyDeposit(depositsId);
=======

>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
        depositService.deleteDepositById(depositsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


<<<<<<< HEAD
}
=======
}
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
