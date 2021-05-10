package com.example.bankingapi.deposit;

import com.example.bankingapi.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;

    @Autowired
    Account account;

    public void getAllDeposits(){
        Iterable<Deposit> allDeposits = depositRepo.findAll();
    }

    public Optional getDepositById(Long depositId){
        return depositRepo.findById(depositId);
    }


    public void createDeposit(Deposit deposit) {
        depositRepo.save(deposit);
        //loop thru all deposits

        //compare the bills to the account that its in and add it to the account
        //for (int i = 0; i < ; i++) {

        //}
    }

    public void updateDeposit(Deposit deposit){
        depositRepo.save(deposit);
    }

    public void deleteDeposit(Deposit depositId) {
        depositRepo.delete(depositId);
    }
}
