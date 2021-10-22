package com.ninja.rmm;

import com.ninja.rmm.controller.CustomerServicesController;
import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.services.CustomerServicesManagement;
import com.ninja.rmm.pojo.services.DeviceInformation;
import com.ninja.rmm.pojo.services.DevicesServices;
import com.ninja.rmm.pojo.services.ServicesInformation;
import com.ninja.rmm.service.CustomerServicesService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerServicesApplicationTests {

    @Mock
    private CustomerServicesService customerServicesService;

    @InjectMocks
    private CustomerServicesController controller;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void newCustomerService() {

        CustomerServicesManagement customerServicesManagement = new CustomerServicesManagement();
        customerServicesManagement.setCustomerId(1L);
        customerServicesManagement.setDeviceName("OMD");
        customerServicesManagement.setDeviceType("WINDOWS_WORK_STATION");

        String response = "Service added successfully : 1";

        when(customerServicesService.addCustomerService(customerServicesManagement)).thenReturn(response);


        ResponseEntity<String> result = controller.newService(customerServicesManagement);
        Assertions.assertEquals("Service added successfully : 1" , result.getBody());

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());


    }

    @Test
    public void obtainCustomerServices() {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerId(1L);

        Collection<DevicesServices> response = new ArrayList<>();
        DevicesServices devicesServices = new DevicesServices();
        Collection<ServicesInformation> servicesInformationList = new ArrayList<>();
        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setDeviceType("WINDOWS_WORK_STATION");
        deviceInformation.setDeviceName("OMD");
        ServicesInformation servicesInformation = new ServicesInformation();
        servicesInformation.setPrice(new BigDecimal(5));
        servicesInformation.setName("ANTIVIRUS");
        servicesInformationList.add(servicesInformation);
        devicesServices.setServices(servicesInformationList);
        devicesServices.setDevice(deviceInformation);
        response.add(devicesServices);


        when(customerServicesService.getServicesByCustomer(customerRequest)).thenReturn(response);

        ResponseEntity<Collection<DevicesServices>> result = controller.obtainServices(customerRequest);


        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());


    }


    @Test
    public void removeService() {

        CustomerServicesManagement customerServicesManagement = new CustomerServicesManagement();

        customerServicesManagement.setCustomerId(1L);
        customerServicesManagement.setServiceName("ANTIVIRUS");
        customerServicesManagement.setDeviceName("OMD");
        customerServicesManagement.setDeviceType("WINDOWS_WORK_STATION");


        String response = "Service deleted successfully!";

        when(customerServicesService.deleteServiceByCustomer(customerServicesManagement)).thenReturn(response);

        ResponseEntity<String> result = controller.removeService(customerServicesManagement);

        Assertions.assertEquals("Service deleted successfully!", result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());


    }


}
