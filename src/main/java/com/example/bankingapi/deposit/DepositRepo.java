package com.example.bankingapi.deposit;

import com.example.bankingapi.account.Account;
import com.example.bankingapi.bill.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {



    @Query(value = "SELECT * FROM Deposit WHERE account_id = ?1", nativeQuery = true)
    List<Deposit> getDepositByAccountId(Long accountId);

    @Query(value = "SELECT * FROM Deposit WHERE payee_id = ?1", nativeQuery = true)
    Optional<Deposit> getDepositByAccountId(Long accountId);
}
}
