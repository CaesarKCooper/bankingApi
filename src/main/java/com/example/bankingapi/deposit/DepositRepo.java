package com.example.bankingapi.deposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {

}
