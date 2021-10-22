package com.ninja.rmm.service.impl;

import com.ninja.rmm.model.Device;
import com.ninja.rmm.pojo.device.DeviceManageRequest;
import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.device.UpdateDeviceRequest;
import com.ninja.rmm.repository.DeviceRepository;
import com.ninja.rmm.service.CustomerDevicesService;
import com.ninja.rmm.util.RmmServicesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CustomerDevicesServiceImpl implements CustomerDevicesService {


    DeviceRepository deviceRepository;
    RmmServicesUtils rmmServicesUtils;

    @Autowired
    public CustomerDevicesServiceImpl(DeviceRepository deviceRepository, RmmServicesUtils rmmServicesUtils) {
        this.deviceRepository = deviceRepository;
        this.rmmServicesUtils = rmmServicesUtils;
    }


    @Override
    public String addCustomerDevice(DeviceManageRequest deviceManageRequest) {

        Device newDevice = new Device();
        Long deviceId;

        try {

            if (rmmServicesUtils.checkDeviceExist(deviceManageRequest) > 0)
                return "Device already exist!";


            if (deviceManageRequest.getCustomerId() != null)
                newDevice.setCustomerId(deviceManageRequest.getCustomerId());
            if (deviceManageRequest.getSystemName() != null)
                newDevice.setSystemName(deviceManageRequest.getSystemName());
            if (deviceManageRequest.getType() != null)
                newDevice.setType(deviceManageRequest.getType());

            newDevice.setStatus("A");
            LocalDateTime currentDateTime = LocalDateTime.now();
            newDevice.setStartDate(currentDateTime);


            deviceRepository.save(newDevice);

            deviceId = newDevice.getId();


        } catch (Exception e) {
            log.error("A problem occurred while trying to add a new customer device: " + e.getMessage());
            return "Could not add the device " + newDevice.getSystemName();
        }

        return "Device added successfully : " + deviceId;

    }

    @Override
    public String updateCustomerDevice(UpdateDeviceRequest updateDeviceRequest) {

        DeviceManageRequest deviceManageRequest = new DeviceManageRequest();

        deviceManageRequest.setSystemName(updateDeviceRequest.getDeviceManageRequest().getSystemName());
        deviceManageRequest.setCustomerId(updateDeviceRequest.getDeviceManageRequest().getCustomerId());
        deviceManageRequest.setType(updateDeviceRequest.getDeviceManageRequest().getType());


        if (rmmServicesUtils.checkDeviceExist(deviceManageRequest) == null)
            return "Device does not exist!";


        try {

            deviceRepository.updateCustomerDevice(updateDeviceRequest.getNewSystemName(), updateDeviceRequest.getNewType(), deviceManageRequest.getCustomerId(), deviceManageRequest.getSystemName(), deviceManageRequest.getType());

        } catch (Exception e) {
            log.error("A problem occurred while trying to update customer device: " + e.getMessage());
            return "Could not update the device " + deviceManageRequest.getSystemName() + " - " + deviceManageRequest.getType();
        }


        return "Device updated successfully !";
    }

    @Override
    public List<Device> getDevicesByCustomer(CustomerRequest customerRequest) {
        List<Device> customerDeviceList = new ArrayList<>();
        try {
            customerDeviceList = deviceRepository.findDevicesByCustomer(customerRequest.getCustomerId());
        } catch (Exception e) {
            log.error("A problem occurred while trying to retrieve customer devices: " + e.getMessage());

        }
        return customerDeviceList;
    }

    @Override
    public String deleteDeviceByCustomer(DeviceManageRequest deviceManageRequest) {

        if (rmmServicesUtils.checkDeviceExist(deviceManageRequest) == null)
            return "Device does not exist!";

        try {
            deviceRepository.deleteCustomerDevice(deviceManageRequest.getCustomerId(), deviceManageRequest.getSystemName(), deviceManageRequest.getType());
        } catch (Exception e) {
            log.error("A problem occurred while trying to delete customer device: " + e.getMessage());
            return "Could not delete the device. Please try again later...";
        }


        return "Device deleted successfully !";
    }


}
