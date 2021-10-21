package com.ninja.rmm.service;

import com.ninja.rmm.model.Device;
import com.ninja.rmm.pojo.device.DeviceManageRequest;
import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.device.UpdateDeviceRequest;

import java.util.List;

public interface CustomerDevicesService {

    String addCustomerDevice(DeviceManageRequest deviceManageRequest);

    String updateCustomerDevice(UpdateDeviceRequest updateDeviceRequest);

    List<Device> getDevicesByCustomer(CustomerRequest customerRequest);

    String deleteDeviceByCustomer(DeviceManageRequest deviceManageRequest);


}
