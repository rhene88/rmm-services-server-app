package com.ninja.rmm.pojo.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDevicesServices {

    private String deviceName;
    private String deviceType;
    private String serviceName;
    private BigDecimal price;
}
