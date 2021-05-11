package com.example.bankingapi.withdrawal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
public class WithdrawalController {

    @Autowired
    WithdrawalService withdrawalService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<Iterable<Withdrawal>> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {

        return new ResponseEntity<>(withdrawalService.getAllWithdrawalsByAccountId(accountId), HttpStatus.OK);
    }

    @RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.GET)
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){

        Optional<Withdrawal> p = withdrawalService.getWithdrawalByWithdrawalId(withdrawalId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawals(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal){

        return new ResponseEntity<>(withdrawalService.createWithdrawal(withdrawal, accountId), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable Long withdrawalId, @RequestBody Withdrawal withdrawal){

        withdrawalService.updateWithdrawal(withdrawal);
        return new ResponseEntity<>("Accepted deposit modification", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){

        withdrawalService.deleteWithdrawal(withdrawalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
