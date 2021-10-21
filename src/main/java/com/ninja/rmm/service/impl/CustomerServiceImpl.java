package com.ninja.rmm.service.impl;

import com.ninja.rmm.model.Customer;
import com.ninja.rmm.pojo.customer.CustomerManagement;
import com.ninja.rmm.repository.CustomerRepository;
import com.ninja.rmm.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Collection<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public String save(CustomerManagement customerManagement) {

        Customer customer = new Customer();

        if (customerManagement.getName() != null)
            customer.setName(customerManagement.getName());
        if (customerManagement.getEmail() != null)
            customer.setEmail(customerManagement.getEmail());
        if (customerManagement.getPassword() != null)
            //customer.setPassword(new BCryptPasswordEncoder().encode(customerManagement.getPassword()));
            customer.setPassword(customerManagement.getPassword());
        if (customerManagement.getRolId() != null)
            customer.setRolId(customerManagement.getRolId());

        customer.setStatus(customerManagement.getStatus());
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            log.error("A problem occurred trying to add a new customer: " + e.getMessage());
            return "Could not add the customer: " + customer.getName();
        }

        return "Customer added successfully!";
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            customerRepository.deleteById(id);
            jsonObject.put("message", "Customer deleted successfully!");
        } catch (Exception e) {
            log.error("A problem occurred while trying to delete customer: " + e.getMessage());
        }

        return jsonObject.toString();
    }


}
