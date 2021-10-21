package com.ninja.rmm.pojo.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerManagement {

    private String name;
    private String email;
    private String password;
    private Long rolId;
    private String status = "A";
}
