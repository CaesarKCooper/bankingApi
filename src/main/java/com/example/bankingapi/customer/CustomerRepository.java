package com.example.bankingapi.customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

//get customer by id, store it in a long
//Long customerId = getCustomerById
//return customerRepository.findById(customerId)
