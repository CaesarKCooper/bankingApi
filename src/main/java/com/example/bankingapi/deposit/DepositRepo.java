package com.example.bankingapi.deposit;

import com.example.bankingapi.account.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {

    Iterable<Deposit> findAllByAccountId(Long payee_id);
}
