package com.ninja.rmm.service;

import com.ninja.rmm.model.Customer;
import com.ninja.rmm.pojo.customer.CustomerManagement;

import java.util.Collection;
import java.util.Optional;

public interface CustomerService {

    Collection<Customer> findAll();

    Optional<Customer> findById(Long id);

    String save(CustomerManagement customerManagement);

    String deleteById(Long id);
}
