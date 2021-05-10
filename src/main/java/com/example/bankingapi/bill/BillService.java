package com.example.bankingapi.bill;
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

        billLog.info("===== RETRIEVING BILL WITH ACCOUNT ID " + id + " =====");
        return billRepo.getBillByAccountId(id);
    }

    public Bill getBillById(Long id) {

        billLog.info("===== RETRIEVING BILL WITH ID " + id + " =====");
        return billRepo.findById(id).orElse(null);
    }


    public List<Bill> getAllBillsByCustomerId(Long customer_id) {

        billLog.info("===== RETRIEVING ALL BILLS FOR CUSTOMER WITH ID " + customer_id + " =====" );
        List<Long> accountId = billRepo.getAccountIdThatMatchesCustomerId(customer_id);
        return billRepo.getBillsRelatedToCustomerId(accountId);
    }

    public Bill createBill(Bill bill) {

        billLog.info("===== CREATING BILL FOR ACCOUNT " + bill.getAccount_id() + " =====");
        return billRepo.save(bill);
    }

    public void updateBill(Bill bill){

        billLog.info("===== UPDATING BILL FOR ACCOUNT " + bill.getAccount_id() + " =====");
        billRepo.save(bill);
    }

    public void deleteBill(Long id){

        billLog.info("===== DELETING BILL FOR ACCOUNT " + id + " =====");
        billRepo.deleteById(id);
    }

}

