package com.example.bankingapi.withdrawal;

import com.example.bankingapi.account.Account;
import com.example.bankingapi.account.AccountService;
import com.example.bankingapi.deposit.Deposit;
import com.example.bankingapi.deposit.DepositController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalService {

    Logger withdrawalLog = LoggerFactory.getLogger(WithdrawalController.class);

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private AccountService accountService;

    public Iterable<Withdrawal> getAllWithdrawalsByAccountId(Long accountId) {

        return withdrawalRepository.getWithdrawalByAccountId(accountId);
    }

    public Withdrawal createWithdrawal(Withdrawal withdrawal, Long accountId) {

        withdrawalLog.info("===== CREATING WITHDRAWAL =====");
        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        Double accountBalance = account.get().getBalance();
        Double withdrawalAmount = withdrawal.getAmount();

        Double transaction = accountBalance - withdrawalAmount;
        account.get().setBalance(transaction);
        return withdrawalRepository.save(withdrawal);
    }

    public Optional<Withdrawal> getWithdrawalByWithdrawalId(Long withdrawalId) {

        withdrawalLog.info("===== GETTING WITHDRAWAL BY WITHDRAWAL ID =====");
        return withdrawalRepository.findById(withdrawalId);
    }

    public void updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) {


    }

    public void deleteWithdrawal(Long withdrawalId) {

        withdrawalLog.info("===== DELETING WITHDRAWAL =====");
        withdrawalRepository.deleteById(withdrawalId);
    }
}
