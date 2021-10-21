package com.ninja.rmm.util;

import com.ninja.rmm.model.CustomerService;
import com.ninja.rmm.model.CustomerServiceId;
import com.ninja.rmm.model.Device;
import com.ninja.rmm.model.Service;
import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.device.DeviceManageRequest;
import com.ninja.rmm.pojo.services.DeviceInformation;
import com.ninja.rmm.pojo.services.DevicesServices;
import com.ninja.rmm.pojo.services.ServicesInformation;
import com.ninja.rmm.repository.CustomerServiceRepository;
import com.ninja.rmm.repository.DeviceRepository;
import com.ninja.rmm.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RmmServicesUtils {

    DeviceRepository deviceRepository;
    CustomerServiceRepository customerServiceRepository;
    ServiceRepository serviceRepository;

    @Autowired
    public RmmServicesUtils(DeviceRepository deviceRepository, CustomerServiceRepository customerServiceRepository, ServiceRepository serviceRepository) {
        this.deviceRepository = deviceRepository;
        this.customerServiceRepository = customerServiceRepository;
        this.serviceRepository = serviceRepository;
    }

    public Long checkDeviceExist(DeviceManageRequest deviceManageRequest) {

        Long deviceId;
        try {
            deviceId = deviceRepository.existById(deviceManageRequest.getCustomerId(), deviceManageRequest.getSystemName(), deviceManageRequest.getType());
        } catch (Exception e) {
            deviceId = null;
        }


        return deviceId;

    }

    public boolean checkServiceExist(CustomerServiceId customerServiceId) {
        int serviceExist;
        boolean response = false;

        try {

            serviceExist = customerServiceRepository.existById(customerServiceId.getCustomerId(), customerServiceId.getDeviceId(), customerServiceId.getServiceId());


        } catch (Exception e) {
            serviceExist = 0;
        }

        if (serviceExist > 0)
            response = true;


        return response;
    }

    public Collection<DevicesServices> getCustomerServicesDetail(List<Device> customerDevices, CustomerRequest customerRequest) {
        Collection<DevicesServices> customerDevicesServicesList = new ArrayList<>();
        for (Device device : customerDevices) {


            Collection<ServicesInformation> servicesInformationList = new ArrayList<>();

            DeviceInformation deviceInformation = new DeviceInformation();
            deviceInformation.setDeviceName(device.getSystemName());
            deviceInformation.setDeviceType(device.getType());

            DevicesServices devicesServices = new DevicesServices();
            devicesServices.setDevice(deviceInformation);


            Collection<CustomerService> servicesId = customerServiceRepository.findAll(customerRequest.getCustomerId(), device.getId());

            for (CustomerService serviceId : servicesId) {


                ServicesInformation servicesInformation = new ServicesInformation();


                try {

                    Optional<Service> service = serviceRepository.findById(serviceId.getCustomerServiceId().getServiceId());

                    servicesInformation.setName(service.get().getName());
                    servicesInformation.setPrice(service.get().getPrice());
                    servicesInformationList.add(servicesInformation);

                } catch (Exception e) {
                    log.error("A problem occurred while trying to get service information: " + e.getMessage());
                }


            }


            devicesServices.setServices(servicesInformationList);
            customerDevicesServicesList.add(devicesServices);


        }

        return customerDevicesServicesList;
    }


}
