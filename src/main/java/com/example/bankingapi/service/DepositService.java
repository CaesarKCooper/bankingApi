package com.example.bankingApi.service;

import com.example.bankingApi.deposit.Deposit;
import com.example.bankingApi.repo.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;

    public void getAllDeposits(){
        Iterable<Deposit> allDeposits = depositRepo.findAll();
    }

    public Optional getDepositById(Long depositId){
        return depositRepo.findById(depositId);
    }


    public void createDeposit(Deposit deposit) {
        depositRepo.save(deposit);
    }

    public void updateDeposit(Deposit deposit){
        depositRepo.save(deposit);
    }

    public void deleteDeposit(Long depositId) {
        depositRepo.delete(depositId);
    }
}
