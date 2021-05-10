package com.example.bankingapi.deposit;

import com.example.bankingapi.account.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {

<<<<<<< HEAD
    Iterable<Deposit> findAllByAccountId(Long payee_id);
}
=======
    //Iterable<Deposit> findAllByAccountId(Long payee_id);
}
>>>>>>> 9a68be8a4380d43dda2191808fda421ff93d506b
