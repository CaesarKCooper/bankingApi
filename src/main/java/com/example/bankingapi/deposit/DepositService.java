package com.example.bankingapi.deposit;

import com.example.bankingapi.account.Account;
import com.example.bankingapi.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;

    @Autowired
    AccountService accountService;
<<<<<<< HEAD
//    @Autowired
//    Account account;

=======

/*
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
    public Iterable<Deposit> getAllDepositsByAccountId(Long accountId){

        Iterable<Deposit> deposits = depositRepo.findAll();

<<<<<<< HEAD
        for (Deposit deposit: deposits) {
            if (deposit.getPayee_id().equals(accountId)){
                return deposits;
            }
        }
        return null;
=======
        for(Deposit deposit : deposits){
            if(deposit.getPayee_id().equals(accountId)){
                return deposits;
            }
        }
       return null;
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
    }
*/

<<<<<<< HEAD
    public Optional<Deposit> getDepositById(Long depositId){
        return depositRepo.findById(depositId);
=======
    public Optional<Deposit> getDepositById(Long depositsId){
        return depositRepo.findById(depositsId);
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
    }


    public Deposit createDeposit(Deposit deposit, Long accountId) {
<<<<<<< HEAD

       Optional<Account> account = accountService.getAccountByAccountId(accountId);
       if (deposit.getAmount() > 0)
       account.get().setBalance(deposit.getAmount());
=======

        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        Double accountBalance = account.get().getBalance();
        Double depositAmount = deposit.getAmount();

        Double transaction = depositAmount += accountBalance;
        account.get().setBalance(transaction);
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b

        return depositRepo.save(deposit);
    }

    public void updateDeposit(Deposit deposits){
        depositRepo.save(deposits);
    }

    public void deleteDepositById(Long depositsId) {
        depositRepo.deleteById(depositsId);
    }
}