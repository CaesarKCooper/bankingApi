package com.example.bankingapi.bill;
import com.example.bankingapi.account.Account;
import com.example.bankingapi.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;


    public List<Bill> getAllBillsByAccountId(Long id) {
        return billRepo.getBillByAccountId(id);
    }

    public Bill getBillById(Long id) {
        return billRepo.findById(id).orElse(null);
    }


    public List<Bill> getAllBillsByCustomerId(Long customer_id) {
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
        return billRepo.save(bill);
    }

    public void updateBill(Bill bill){
        billRepo.save(bill);
    }

    public void deleteBill(Long id){
        billRepo.deleteById(id);
    }

}

