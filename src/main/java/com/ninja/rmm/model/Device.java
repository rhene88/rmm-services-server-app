package com.ninja.rmm.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="RMM_DEVICE")
@Table(name="RMM_DEVICE")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="SYSTEM_NAME")
    private String systemName;
    @Column(name="TYPE")
    private  String type;
    @Column(name="CUSTOMER_ID")
    private Long customerId;
    @Column(name="STATUS")
    private String status;
    @Column(name="START_DATE")
    private LocalDateTime startDate;
    @Column(name="END_DATE")
    private LocalDateTime endDate;
}
