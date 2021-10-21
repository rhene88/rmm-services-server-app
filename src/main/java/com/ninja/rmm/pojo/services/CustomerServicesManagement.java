package com.ninja.rmm.pojo.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServicesManagement {

    private Long customerId;
    private String deviceName;
    private String deviceType;
    private String serviceName;

}
