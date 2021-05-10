package com.example.bankingapi.deposit;

import com.example.bankingapi.account.Account;
import com.example.bankingapi.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;

    @Autowired
    AccountService accountService;

/*
    public Iterable<Deposit> getAllDepositsByAccountId(Long accountId){

        Iterable<Deposit> deposits = depositRepo.findAll();

        for(Deposit deposit : deposits){
            if(deposit.getPayee_id().equals(accountId)){
                return deposits;
            }
        }
       return null;
    }
*/

    public Optional<Deposit> getDepositById(Long depositsId){
        return depositRepo.findById(depositsId);
    }


    public Deposit createDeposit(Deposit deposit, Long accountId) {

        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        Double accountBalance = account.get().getBalance();
        Double depositAmount = deposit.getAmount();

        Double transaction = depositAmount += accountBalance;
        account.get().setBalance(transaction);

        return depositRepo.save(deposit);
    }

    public void updateDeposit(Deposit deposit){
        depositRepo.save(deposit);
    }

    public void deleteDepositById(Long depositsId) {
        depositRepo.deleteById(depositsId);
    }
}