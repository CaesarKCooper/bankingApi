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

    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Deposit>> getAllDepositsByAccountId(@PathVariable Long accountId) {

        return new ResponseEntity<>(depositService.getAllDepositsByAccountId(accountId), HttpStatus.OK);
    }

    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepositById(@PathVariable Long depositsId) {

        return new ResponseEntity<>(depositService.getDepositByDepositId(depositsId),HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.POST)
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit){

        return new ResponseEntity<>(depositService.createDeposit(deposit, accountId), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositsId, @RequestBody Deposit deposit){

        depositService.updateDeposit(deposit, depositsId);
        return new ResponseEntity<>("Accepted deposit modification", HttpStatus.OK);
    }

    @RequestMapping(value = "/deposits/{depositsId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositsId) {

        depositService.deleteDeposit(depositsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}