package com.ninja.rmm.service.impl;

import com.ninja.rmm.model.CustomerService;
import com.ninja.rmm.model.CustomerServiceId;
import com.ninja.rmm.model.Device;
import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.device.DeviceManageRequest;
import com.ninja.rmm.pojo.services.CustomerServicesManagement;
import com.ninja.rmm.pojo.services.DevicesServices;
import com.ninja.rmm.repository.CustomerServiceRepository;
import com.ninja.rmm.repository.DeviceRepository;
import com.ninja.rmm.repository.ServiceRepository;
import com.ninja.rmm.service.CustomerServicesService;
import com.ninja.rmm.util.RmmServicesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class CustomerServicesServiceImpl implements CustomerServicesService {

    CustomerServiceRepository customerServiceRepository;
    ServiceRepository serviceRepository;
    RmmServicesUtils rmmServicesUtils;
    DeviceRepository deviceRepository;

    @Autowired
    public CustomerServicesServiceImpl(CustomerServiceRepository customerServiceRepository, ServiceRepository serviceRepository, RmmServicesUtils rmmServicesUtils, DeviceRepository deviceRepository) {
        this.customerServiceRepository = customerServiceRepository;
        this.serviceRepository = serviceRepository;
        this.rmmServicesUtils = rmmServicesUtils;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public String addCustomerService(CustomerServicesManagement customerServicesManagement) {

        CustomerService customerService = new CustomerService();
        CustomerServiceId customerServiceId = new CustomerServiceId();

        DeviceManageRequest deviceManageRequest = new DeviceManageRequest();
        Long deviceId;
        Long serviceId;

        if (customerServicesManagement.getCustomerId() != null)
            deviceManageRequest.setCustomerId(customerServicesManagement.getCustomerId());
        if (customerServicesManagement.getDeviceName() != null)
            deviceManageRequest.setSystemName(customerServicesManagement.getDeviceName());
        if (customerServicesManagement.getDeviceType() != null)
            deviceManageRequest.setType(customerServicesManagement.getDeviceType());

        try {

            deviceId = rmmServicesUtils.checkDeviceExist(deviceManageRequest);


            if (deviceId == null)
                return "Device does not exist!";

            serviceId = serviceRepository.existByName(customerServicesManagement.getServiceName(), customerServicesManagement.getDeviceType());

            if (customerServicesManagement.getCustomerId() != null)
                customerServiceId.setCustomerId(customerServicesManagement.getCustomerId());

            customerServiceId.setServiceId(serviceId);
            customerServiceId.setDeviceId(deviceId);

            if (rmmServicesUtils.checkServiceExist(customerServiceId))
                return "Service already exist!";
            customerService.setCustomerServiceId(customerServiceId);
            customerService.setStatus("A");
            LocalDateTime currentDateTime = LocalDateTime.now();
            customerService.setStartDate(currentDateTime);

            customerServiceRepository.save(customerService);

        } catch (Exception e) {
            log.error("A problem occurred while trying to add a new service: " + e.getMessage());
            return "Could not add the service: " + customerServicesManagement.getServiceName();
        }


        return "Service added successfully: " + customerServicesManagement.getServiceName();
    }

    @Override
    public Collection<DevicesServices> getServicesByCustomer(CustomerRequest customerRequest) {
        Collection<DevicesServices> customerDevicesServicesList = new ArrayList<>();
        List<Device> customerDevices = new ArrayList<>();

        try {

            customerDevices = deviceRepository.findDevicesByCustomer(customerRequest.getCustomerId());

            if (customerDevices.isEmpty() || customerDevices == null)
                return customerDevicesServicesList;


            customerDevicesServicesList = rmmServicesUtils.getCustomerServicesDetail(customerDevices, customerRequest);

        } catch (Exception e) {
            log.error("A problem occurred while trying to retrieve customer services: " + e.getMessage());

        }
        return customerDevicesServicesList;
    }

    @Override
    public String deleteServiceByCustomer(CustomerServicesManagement customerServicesManagement) {

        DeviceManageRequest deviceManageRequest = new DeviceManageRequest();
        CustomerServiceId customerServiceId = new CustomerServiceId();
        Long serviceId;
        Long deviceId;

        deviceManageRequest.setCustomerId(customerServicesManagement.getCustomerId());
        deviceManageRequest.setSystemName(customerServicesManagement.getDeviceName());
        deviceManageRequest.setType(customerServicesManagement.getDeviceType());

        deviceId = rmmServicesUtils.checkDeviceExist(deviceManageRequest);

        if (deviceId == null)
            return "Device does not exist!";

        serviceId = serviceRepository.existByName(customerServicesManagement.getServiceName(), customerServicesManagement.getDeviceType());

        if (serviceId == null)
            return "Service does not exist!";

        customerServiceId.setCustomerId(customerServicesManagement.getCustomerId());
        customerServiceId.setServiceId(serviceId);
        customerServiceId.setDeviceId(deviceId);


        try {
            customerServiceRepository.deleteById(customerServiceId);


        } catch (Exception e) {
            log.error("A problem occurred while trying to remove service: " + e.getMessage());
        }


        return "Service deleted successfully!";
    }
}
