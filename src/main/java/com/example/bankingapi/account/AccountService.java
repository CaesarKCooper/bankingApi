package com.example.bankingapi.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
public class AccountService {

    Logger accountLog = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    public Iterable<Account> getAllAccounts(){

        accountLog.info("===== RETRIEVING ALL ACCOUNTS =====");
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountByAccountId(Long accountId){

        accountLog.info("===== RETRIEVING ACCOUNT WITH ID " + accountId + " =====");
        return accountRepository.findById(accountId);
    }

    public Iterable<Account> getAllAccountsByCustomerId(Long customerId){

        accountLog.info("===== RETRIEVING ALL ACCOUNTS FOR CUSTOMER WITH ID " + customerId + " =====" );
        return accountRepository.findAllByCustomerId(customerId);
    }

    public Account createAccount(Account account, Long customerId){

        accountLog.info("===== CREATING ACCOUNT FOR CUSTOMER WITH ID " + customerId + " =====");
        account.setCustomerId(customerId);
        return accountRepository.save(account);

    }

    public void updateAccount(Account account){

        accountLog.info("===== UPDATING ACCOUNT WITH ID " + account.getId() + " =====");
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId){

        accountLog.info("===== DELETING ACCOUNT WITH ID " + accountId + " =====");
        accountRepository.deleteById(accountId);
    }

}