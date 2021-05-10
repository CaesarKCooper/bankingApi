package com.example.bankingapi.customer;

import com.example.bankingapi.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    private Object Optional;
    private Object Customer;

    public void createCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {

        List<Customer> listOfCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(listOfCustomers::add);
        return listOfCustomers;
    }

    public Optional<Customer> getCustomerByAccountId(Long account_id) {

        Long customerId = accountRepository.findById(account_id).get().getCustomerId();
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}