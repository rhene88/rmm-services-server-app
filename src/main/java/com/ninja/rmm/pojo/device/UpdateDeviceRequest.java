package com.ninja.rmm.pojo.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeviceRequest {

    private DeviceManageRequest deviceManageRequest;
    private String newSystemName;
    private String newType;

}
