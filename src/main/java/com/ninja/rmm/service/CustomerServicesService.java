package com.ninja.rmm.service;

import com.ninja.rmm.pojo.device.CustomerRequest;
import com.ninja.rmm.pojo.services.CustomerServicesManagement;
import com.ninja.rmm.pojo.services.DevicesServices;

import java.util.Collection;

public interface CustomerServicesService {

    String addCustomerService(CustomerServicesManagement customerServicesManagement);

    Collection<DevicesServices> getServicesByCustomer(CustomerRequest customerRequest);

    String deleteServiceByCustomer(CustomerServicesManagement customerServicesManagement);


}
