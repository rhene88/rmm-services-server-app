package com.ninja.rmm.pojo.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceManageRequest {

    private Long customerId;
    private String systemName;
    private String type;
}
