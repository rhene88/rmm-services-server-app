package com.ninja.rmm;

import com.ninja.rmm.controller.CustomerDevicesController;
import com.ninja.rmm.model.Device;
import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.device.DeviceManageRequest;
import com.ninja.rmm.pojo.device.UpdateDeviceRequest;
import com.ninja.rmm.service.CustomerDevicesService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerDeviceApplicationTests {

    @Mock
    private CustomerDevicesService customerDeviceServices;

    @InjectMocks
    private CustomerDevicesController controller;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void newCustomerDevice() {

        DeviceManageRequest addingDeviceRequest = new DeviceManageRequest();
        addingDeviceRequest.setCustomerId(1L);
        addingDeviceRequest.setSystemName("OMD");
        addingDeviceRequest.setType("WINDOWS_WORK_STATION");

        String response = "Device added successfully : 1";

        when(customerDeviceServices.addCustomerDevice(addingDeviceRequest)).thenReturn(response);

        ResponseEntity<String> result = controller.newCustomerDevice(addingDeviceRequest);

        Assertions.assertEquals("Device added successfully : 1", result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());


    }

    @Test
    public void obtainCustomerDevice() {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerId(1L);

        List<Device> response = new ArrayList<>();
        Device device = new Device();
        device.setCustomerId(1L);
        device.setSystemName("OMD");
        device.setType("WINDOWS_WORK_STATION");
        response.add(device);


        when(customerDeviceServices.getDevicesByCustomer(customerRequest)).thenReturn(response);

        ResponseEntity<List<Device>> result = controller.obtainCustomerDevice(customerRequest);

        Assertions.assertEquals("OMD", result.getBody().get(0).getSystemName());
        Assertions.assertEquals("WINDOWS_WORK_STATION", result.getBody().get(0).getType());

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());


    }

    @Test
    public void updateCustomerDevice() {

        DeviceManageRequest deviceManageRequest = new DeviceManageRequest();

        deviceManageRequest.setCustomerId(1L);
        deviceManageRequest.setSystemName("OMD");
        deviceManageRequest.setType("WINDOWS_WORK_STATION");

        UpdateDeviceRequest updateDeviceRequest = new UpdateDeviceRequest();

        updateDeviceRequest.setDeviceManageRequest(deviceManageRequest);
        updateDeviceRequest.setNewSystemName("UB40");
        updateDeviceRequest.setNewType("MAC");


        String response = "Device updated successfully : 1";

        when(customerDeviceServices.updateCustomerDevice(updateDeviceRequest)).thenReturn(response);

        ResponseEntity<String> result = controller.updateCustomerDevice(updateDeviceRequest);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());


    }

    @Test
    public void removeCustomerDevice() {

        DeviceManageRequest deviceManageRequest = new DeviceManageRequest();

        deviceManageRequest.setCustomerId(1L);
        deviceManageRequest.setSystemName("OMD");
        deviceManageRequest.setType("WINDOWS_WORK_STATION");


        String response = "Device deleted successfully : 1";

        when(customerDeviceServices.deleteDeviceByCustomer(deviceManageRequest)).thenReturn(response);

        ResponseEntity<String> result = controller.removeCustomerDevice(deviceManageRequest);

        Assertions.assertEquals("Device deleted successfully : 1", result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());


    }


}
