package com.example.bankingapi.deposit;

import com.example.bankingapi.account.Account;
import com.example.bankingapi.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;

    @Autowired
    AccountService accountService;
//    @Autowired
//    Account account;

    public Iterable<Deposit> getAllDepositsByAccountId(Long accountId){

        Iterable<Deposit> deposits = depositRepo.findAll();

        for (Deposit deposit: deposits) {
            if (deposit.getPayee_id().equals(accountId)){
                return deposits;
            }
        }
        return null;
    }

    public Optional<Deposit> getDepositById(Long depositId){
        return depositRepo.findById(depositId);
    }


    public Deposit createDeposit(Deposit deposit, Long accountId) {

//       Optional<Account> account = accountService.getAccountByAccountId(accountId);
//       if (deposit.getAmount() > 0)
//       account.get().setBalance(deposit.getAmount());

        return depositRepo.save(deposit);
    }

    public void updateDeposit(Deposit deposits){
        depositRepo.save(deposits);
    }

    public void deleteDepositById(Long depositsId) {
        depositRepo.deleteById(depositsId);
    }
}
