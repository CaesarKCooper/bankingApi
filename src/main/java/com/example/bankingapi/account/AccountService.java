package com.example.bankingapi.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Iterable<Account> getAllAccounts(){

        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long accountId){

        return accountRepository.findById(accountId);
    }

        public Iterable<Account> getAllAccountsByCustomer(Long customerId){

        //list of account (repository)
        //loop through list of accounts
        //compare accounts to customer id
        //return matching accounts

            return accountRepository.findAllByCustomerId(customerId);
    }

    public void createAccount(Account account){

        accountRepository.save(account);
    }

    public void updateAccount(Account account){

        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId){

        accountRepository.deleteById(accountId);
    }

}
