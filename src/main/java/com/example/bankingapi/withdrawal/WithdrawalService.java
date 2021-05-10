package com.example.bankingapi.withdrawal;

import com.example.bankingapi.account.Account;
import com.example.bankingapi.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawalService {

    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private AccountService accountService;


    public  Iterable<Withdrawal> getAllWithdrawalsByAccountId(Long accountId) {
        Iterable<Withdrawal> withdrawals = withdrawalRepository.findAll();
        for (Withdrawal withdrawal: withdrawals) {
            if (withdrawal.getPayer_id().equals(accountId)){
                return withdrawals;
            }
        }
        return null;
    }


    public Withdrawal createWithdrawal(Withdrawal withdrawal, Long accountId) {
        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        Double accountBalance = account.get().getBalance();
        Double withdrawalAmount = withdrawal.getAmount();

        Double transaction = accountBalance - withdrawalAmount;
        account.get().setBalance(transaction);
        return withdrawalRepository.save(withdrawal);
    }

    public Optional<Withdrawal> getWithdrawalById(Long withdrawalId) {
        return withdrawalRepository.findById(withdrawalId);
    }

    public void updateWithdrawal(Withdrawal withdrawal) {
        withdrawalRepository.save(withdrawal);
    }

    public void deleteWithdrawal(Long withdrawalId) {
        withdrawalRepository.deleteById(withdrawalId);
    }

}
