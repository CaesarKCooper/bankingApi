package com.example.bankingApi.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public void createCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> listOfCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(listOfCustomers::add);
        return listOfCustomers;
    }

    public Optional<Customer> getCustomerByAccountId(Long account_id) {
        return customerRepository.findById(account_id);
    }

    public Optional<Customer> getCustomerById(Long id) {

        //go over list of customers
        //compare each account selected id
        //return account that matches
        return customerRepository.findById(id);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
