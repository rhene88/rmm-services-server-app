package com.ninja.rmm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="RMM_SERVICE")
public class Service {

    @Id
    @Column(name="ID")
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="PRICE")
    private BigDecimal price;
    @Column(name="STATUS")
    private String status;
    @Column(name="DEVICE_TYPE")
    private String deviceType;

}
