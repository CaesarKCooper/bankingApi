package com.example.bankingapi.deposit;
import com.example.bankingapi.account.Account;
import com.example.bankingapi.account.AccountService;
import com.example.bankingapi.customer.CustomerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    Logger depositLog = LoggerFactory.getLogger(DepositController.class);

    @Autowired
    DepositRepo depositRepo;

    @Autowired
    AccountService accountService;

    public Iterable<Deposit> getAllDepositsByAccountId(Long accountId){

        depositLog.info("===== RETRIEVING ALL DEPOSITS BY ACCOUNT ID =====");
        return depositRepo.getDepositsByAccountId(accountId);
    }

    public Optional<Deposit> getDepositByAccountId(Long accountId){

        return depositRepo.getDepositByAccountId(accountId);
    }

    public Optional<Deposit> getDepositByDepositId(Long depositsId){

        depositLog.info("===== RETRIEVING DEPOSIT BY DEPOSIT ID =====");
        return depositRepo.findById(depositsId);
    }

    public Deposit createDeposit(Deposit deposit, Long accountId) {

        depositLog.info("===== CREATING DEPOSIT =====");
        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        Double accountBalance = account.get().getBalance();
        Double depositAmount = deposit.getAmount();

        Double transaction = depositAmount + accountBalance;
        account.get().setBalance(transaction);
        return depositRepo.save(deposit);
    }

    public void updateDeposit(Deposit deposit, Long depositId){

        depositLog.info("===== UPDATING DEPOSIT =====");

        Account account = accountService.getAccountByAccountId(deposit.getPayee_id()).orElse(null);

        Double oldDepositAmount = depositRepo.findById(depositId).get().getAmount();

        Double accountBalance = account.getBalance();

        Double oldBalance = accountBalance - oldDepositAmount;
        account.setBalance(oldBalance);

        Double depositAmount = deposit.getAmount();

        Double transaction = oldBalance + depositAmount;
        account.setBalance(transaction);

        depositRepo.save(deposit);
    }

    public void deleteDeposit(Long depositsId) {

        depositLog.info("===== DELETING DEPOSIT =====");
        depositRepo.deleteById(depositsId);
    }
}