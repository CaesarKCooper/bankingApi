package com.example.bankingApi.account;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccounts(){

        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId){


        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}/accounts")
    public Iterable<Account> getAllAccountsByCustomer(@PathVariable Long customerId){

        return accountService.getAllAccountsByCustomer(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account account){

        accountService.createAccount(account);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account account){

        accountService.updateAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId){

        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
