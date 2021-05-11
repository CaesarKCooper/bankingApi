package com.example.bankingapi.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    Logger accountLog = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountRepository accountRepository;

    public Iterable<Account> getAllAccounts(){

        accountLog.info("===== RETRIEVING ALL ACCOUNTS =====");
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountByAccountId(Long accountId){

        accountLog.info("===== RETRIEVING ALL ACCOUNTS BY ACCOUNT ID =====");
        return accountRepository.findById(accountId);
    }

    public Iterable<Account> getAllAccountsByCustomer(Long customerId){

        accountLog.info("===== RETRIEVING ALL ACCOUNTS BY CUSTOMER ID =====");
        return accountRepository.findAllByCustomerId(customerId);
    }

    public Account createAccount(Account account){

        accountLog.info("===== CREATING ACCOUNT =====");
        return accountRepository.save(account);
    }

    public void updateAccount(Account account){

        accountLog.info("===== UPDATING ACCOUNT =====");
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId){

        accountLog.info("===== DELETING ACCOUNT =====");
        accountRepository.deleteById(accountId);
    }
}