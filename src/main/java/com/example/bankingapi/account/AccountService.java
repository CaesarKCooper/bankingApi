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

            return accountRepository.findAllByCustomerId(customerId);
    }

    public void createAccount(Account account, Long customerId){

        account.setCustomerId(customerId);
        accountRepository.save(account);

    }

    public void updateAccount(Account account){

        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId){

        accountRepository.deleteById(accountId);
    }

}
