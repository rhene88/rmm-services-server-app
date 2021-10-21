package com.ninja.rmm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceId implements Serializable {

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;
    @Column(name = "DEVICE_ID", nullable = false)
    private Long deviceId;
    @Column(name = "SERVICE_ID", nullable = false)
    private Long serviceId;


}
