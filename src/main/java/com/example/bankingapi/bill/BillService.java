package com.example.bankingapi.bill;
import com.example.bankingapi.account.Account;
import com.example.bankingapi.account.AccountController;
import com.example.bankingapi.account.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    Logger billLog = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillRepo billRepo;

    public List<Bill> getAllBillsByAccountId(Long id) {

        billLog.info("===== RETRIEVING ALL BILLS BY ACCOUNT ID =====");
        return billRepo.getBillByAccountId(id);
    }

    public Bill getBillByBillId(Long id) {

        billLog.info("===== RETRIEVING BILL BY BILL ID =====");
        return billRepo.findById(id).orElse(null);
    }

    public List<Bill> getAllBillsByCustomerId(Long customer_id) {

        billLog.info("===== RETRIEVING ALL BILLS BY CUSTOMER ID =====");
        List<Long> accountId = billRepo.getAccountIdThatMatchesCustomerId(customer_id);
        return billRepo.getBillsThatMatchAccountIdInBillWithAccountIdInAccountToUseAfterFindingCustomerByIdInAccount(accountId);
    }

    @Autowired AccountRepository accountRepository;
    public boolean accountCheck(Long accountId){

        Account account = accountRepository.findById(accountId).orElse(null);
        return account != null;
    }

    public boolean billCheck(Long billId){

        Bill bill = billRepo.findById(billId).orElse(null);
        return bill != null;
    }

    public Bill createBill(Bill bill) {

        billLog.info("===== CREATING BILL =====");
        return billRepo.save(bill);
    }

    public void updateBill(Bill bill){

        billLog.info("===== UPDATING BILL =====");
        billRepo.save(bill);
    }

    public void deleteBill(Long id){

        billLog.info("===== DELETING BILL =====");
        billRepo.deleteById(id);
    }
}

