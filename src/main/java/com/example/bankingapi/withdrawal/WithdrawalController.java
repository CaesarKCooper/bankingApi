package com.example.bankingapi.withdrawal;

import com.example.bankingapi.account.Account;
import com.example.bankingapi.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@RestController
public class WithdrawalController {

    @Autowired
    WithdrawalService withdrawalService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}/withdrawals}")
    public ResponseEntity<Iterable<Withdrawal>> getAllWithdrawals() {
        return new ResponseEntity<>(withdrawalService.getAllWithdrawals(), HttpStatus.OK);
    }

    @RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.GET)
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){
        Optional<Customer> p = withdrawalService.getWithdrawalById(withdrawalId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawals(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal){

        withdrawalService.createWithdrawal(withdrawal, accountId);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable Long withdrawalId, @RequestBody Withdrawal withdrawal){

        withdrawalService.updateWithdrawal(withdrawalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawals(@PathVariable Long withdrawalId){

        withdrawalService.deleteWithdrawal(withdrawalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
