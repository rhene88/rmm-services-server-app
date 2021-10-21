package com.ninja.rmm.pojo.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DevicesServices {

    private DeviceInformation device;
    private Collection<ServicesInformation> services;
}
