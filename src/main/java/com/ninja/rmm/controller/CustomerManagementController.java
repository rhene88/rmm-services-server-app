package com.ninja.rmm.controller;

import com.ninja.rmm.model.Customer;
import com.ninja.rmm.pojo.customer.CustomerManagement;
import com.ninja.rmm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("${swagger.service.contextPath}")
public class CustomerManagementController {

    CustomerService customerService;

    @Autowired
    public CustomerManagementController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer", produces = "application/json")
    public ResponseEntity<String> newCustomer(@RequestBody CustomerManagement customerManagement) {

        String customerResponse = customerService.save(customerManagement);

        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/customer", produces = "application/json")
    public ResponseEntity<Collection<Customer>> obtainCustomers() {

        Collection<Customer> customerList = customerService.findAll();

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }


}
