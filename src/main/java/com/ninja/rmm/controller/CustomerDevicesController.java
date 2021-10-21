package com.ninja.rmm.controller;

import com.ninja.rmm.model.Device;
import com.ninja.rmm.pojo.device.DeviceManageRequest;
import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.device.UpdateDeviceRequest;
import com.ninja.rmm.service.CustomerDevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${swagger.service.contextPath}")
public class CustomerDevicesController {

    CustomerDevicesService service;

    @Autowired
    public CustomerDevicesController(CustomerDevicesService service) {
        this.service = service;
    }


    @PostMapping(value = "/device", produces = "application/json")
    public ResponseEntity<String> newCustomerDevice(@RequestBody DeviceManageRequest addingDeviceRequest) {

        String response = service.addCustomerDevice(addingDeviceRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/device", produces = "application/json")
    public ResponseEntity<List<Device>> obtainCustomerDevice(@RequestBody CustomerRequest customerRequest) {

        List<Device> customerDeviceList = service.getDevicesByCustomer(customerRequest);

        return new ResponseEntity<>(customerDeviceList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/device", produces = "application/json")
    public ResponseEntity<String> removeCustomerDevice(@RequestBody DeviceManageRequest addingDeviceRequest) {

        String response = service.deleteDeviceByCustomer(addingDeviceRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/device", produces = "application/json")
    public ResponseEntity<String> updateCustomerDevice(@RequestBody UpdateDeviceRequest updateDeviceRequest) {

        String response = service.updateCustomerDevice(updateDeviceRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
