package com.example.bankingapi.customer;


import com.example.bankingapi.bill.BillController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    Logger customerLog = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){

        customerLog.info("===== CREATING CUSTOMER =====");
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {

        customerLog.info("===== RETRIEVING ALL CUSTOMERS =====");
        List<Customer> listOfCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(listOfCustomers::add);
        return listOfCustomers;
    }

    public Optional<Customer> getCustomerByAccountId(Long account_id) {

        customerLog.info("===== RETRIEVING CUSTOMER BY ACCOUNT ID =====");
        return customerRepository.findById(account_id);
    }

    public Optional<Customer> getCustomerByCustomerId(Long id) {

        customerLog.info("===== RETRIEVING CUSTOMER BY CUSTOMER ID =====");
        return customerRepository.findById(id);
    }

    public void updateCustomer(Customer customer) {

        customerLog.info("===== UPDATING CUSTOMER =====");
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {

        customerLog.info("===== DELETING CUSTOMER =====");
        customerRepository.deleteById(id);
    }
}
