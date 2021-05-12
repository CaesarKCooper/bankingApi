package com.example.bankingapi.account;

import com.example.bankingapi.exceptionhandling.CodeData;
import com.example.bankingapi.exceptionhandling.CodeMessage;
import com.example.bankingapi.exceptionhandling.CodeMessageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public ResponseEntity<?> getAllAccounts() {

        Iterable<Account> accounts = accountService.getAllAccounts();
        if (accounts.iterator().hasNext()) {
            CodeMessageData response = new CodeMessageData(200, "Success", accounts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        CodeMessage exception = new CodeMessage(404,"Error fetching accounts");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {

        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        if (account.isEmpty()) {
            CodeMessage exception = new CodeMessage(404,"Error fetching account with id: " + accountId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        CodeMessageData response = new CodeMessageData(200, "Success", account);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllAccountsByCustomer(@PathVariable Long customerId) {

        Iterable<Account> accounts = accountService.getAllAccountsByCustomer(customerId);
        if (accounts.iterator().hasNext()) {
            CodeMessageData response = new CodeMessageData(200, "Success", accounts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        CodeMessage exception = new CodeMessage(404,"Error fetching accounts");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account account) {

        Account account1 = accountService.createAccount(account);
        if (accountService.customerCheck(customerId)) {
            CodeMessageData response = new CodeMessageData(201, "Account created", account1);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        CodeMessage exception = new CodeMessage(404,"Error creating account: Customer not found");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {

        if(accountService.accountCheck(accountId)){
            accountService.updateAccount(account);
            CodeMessage response = new CodeMessage(201, "Customer account updated");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        CodeMessage exception = new CodeMessage(404, "Error: Account not found");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {

        if (accountService.accountCheck(accountId)) {
            accountService.deleteAccount(accountId);
            CodeMessage response = new CodeMessage(202 ,"Account successfully deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        CodeMessage exception = new CodeMessage(404,"Error: Account not found");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}