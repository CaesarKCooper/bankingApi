package com.example.bankingapi.bill;

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

        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable Long billId){

        return new ResponseEntity<>(billService.getBillById(billId), HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillsByCustomerId(@PathVariable Long customerId){
        return new ResponseEntity<>(billService.getAllBillsByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill){


        return new ResponseEntity<>(billService.createBill(bill), HttpStatus.CREATED);
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill){
        billService.updateBill(bill);

        return new ResponseEntity<>("Accepted bill modification", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId){
        billService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
