package com.example.bankingapi.account;

import com.example.bankingapi.BankingApiApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccounts(){


        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK); //success
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}")
    public ResponseEntity<?> getAccountByAccountId(@PathVariable Long accountId){

        return new ResponseEntity<>(accountService.getAccountByAccountId(accountId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}/accounts")
    public Iterable<Account> getAllAccountsByCustomerId(@PathVariable Long customerId){

        return accountService.getAllAccountsByCustomerId(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account account){


        return new ResponseEntity<>(accountService.createAccount(account, customerId), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account account){

        accountService.updateAccount(account);
        return new ResponseEntity<>("Customer account updated", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId){

        accountService.deleteAccount(accountId);
        return new ResponseEntity<>("Account successfully deleted", HttpStatus.OK);
    }
}