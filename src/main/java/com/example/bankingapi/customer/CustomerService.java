package com.example.bankingapi.customer;

import com.example.bankingapi.account.AccountController;
import com.example.bankingapi.account.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    Logger customerLog = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    private Object Optional;
    private Object Customer;

    public Customer createCustomer(Customer customer){

        customerLog.info("===== CREATING CUSTOMER " + customer.getFirst_name() + ", " + customer.getLast_name() + " =====");
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {

        customerLog.info("===== RETRIEVING ALL CUSTOMERS =====");
        List<Customer> listOfCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(listOfCustomers::add);
        return listOfCustomers;
    }

    public Optional<Customer> getCustomerByAccountId(Long account_id) {

        customerLog.info("===== RETRIEVING CUSTOMER WITH ACCOUNT ID " + account_id + " =====");
        Long customerId = accountRepository.findById(account_id).get().getCustomerId();
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> getCustomerById(Long id) {

        customerLog.info("===== RETRIEVING CUSTOMER WITH ID " + id + " =====");
        return customerRepository.findById(id);
    }

    public void updateCustomer(Customer customer) {

        customerLog.info("===== UPDATING CUSTOMER WITH ID " + customer.getId() + " =====");
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {

        customerLog.info("===== DELETING CUSTOMER WITH ID " + id + " =====");
        customerRepository.deleteById(id);
    }

}