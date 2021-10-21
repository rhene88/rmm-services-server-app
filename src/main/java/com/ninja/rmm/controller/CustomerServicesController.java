package com.ninja.rmm.controller;

import com.ninja.rmm.model.Customer;
import com.ninja.rmm.pojo.customer.CustomerManagement;
import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.device.DeviceManageRequest;
import com.ninja.rmm.pojo.services.CustomerServicesManagement;
import com.ninja.rmm.pojo.services.DevicesServices;
import com.ninja.rmm.service.CustomerServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("${swagger.service.contextPath}")
public class CustomerServicesController {

    CustomerServicesService customerServicesService;

    @Autowired
    public CustomerServicesController(CustomerServicesService customerServicesService) {
        this.customerServicesService = customerServicesService;
    }

    @PostMapping(value = "/service", produces = "application/json")
    public ResponseEntity<String> newService(@RequestBody CustomerServicesManagement customerServicesManagement) {

        String serviceResponse = customerServicesService.addCustomerService(customerServicesManagement);

        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/service", produces = "application/json")
    public ResponseEntity<Collection<DevicesServices>> obtainServices(@RequestBody CustomerRequest customerRequest) {

        Collection<DevicesServices> servicesList = customerServicesService.getServicesByCustomer(customerRequest);

        return new ResponseEntity<>(servicesList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/service", produces = "application/json")
    public ResponseEntity<String> removeService(@RequestBody CustomerServicesManagement customerServicesManagement) {

        String response = customerServicesService.deleteServiceByCustomer(customerServicesManagement);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
