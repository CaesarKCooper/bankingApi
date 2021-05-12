package com.example.bankingapi.bill;

import com.example.bankingapi.exceptionhandling.CodeData;
import com.example.bankingapi.exceptionhandling.CodeMessage;
import com.example.bankingapi.exceptionhandling.CodeMessageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsByAccount(@PathVariable Long accountId) {

        List<Bill> bills = billService.getAllBillsByAccountId(accountId);
        if(bills.isEmpty()){
            CodeMessage exception = new CodeMessage("error fetching bills");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        CodeData response = new CodeData(200, bills);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable Long billId){

        Bill bill = billService.getBillByBillId(billId);
        if(bill == null){
            CodeMessage exception = new CodeMessage("error fetching bill with id: " + billId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        CodeData response = new CodeData(200, bill);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillsByCustomerId(@PathVariable Long customerId){

        List<Bill> bills = billService.getAllBillsByCustomerId(customerId);
        if(bills.isEmpty()){
            CodeMessage exception = new CodeMessage("error fetching bills");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        CodeData response = new CodeData(200, bills);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable Long accountId){

        if(!billService.accountCheck(accountId)){
            CodeMessage exception = new CodeMessage("Error creating bill: Account not found");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        Bill b1 = billService.createBill(bill);
        CodeMessageData response = new CodeMessageData(201, "Created bill and added it to the account", b1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId){

        if(!billService.billCheck(billId)){
            CodeMessage exception = new CodeMessage("Bill ID does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        billService.updateBill(bill);
        CodeMessage response = new CodeMessage(202, "Accepted bill modification");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId){

        if(!billService.billCheck(billId)){
            CodeMessage exception = new CodeMessage("This id does not exist in bills");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        billService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
