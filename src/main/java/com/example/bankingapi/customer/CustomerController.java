package com.example.bankingapi.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

        return new ResponseEntity<>(customerService.createCustomer(customer),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {

        List<Customer> p = customerService.getAllCustomers();
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{account_id}/customer", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerByAccount(@PathVariable Long account_id){

        Optional<Customer> p = customerService.getCustomerByAccountId(account_id);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){

        Optional<Customer> p = customerService.getCustomerByCustomerId(id);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @RequestMapping(value= "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id){

        customerService.updateCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}