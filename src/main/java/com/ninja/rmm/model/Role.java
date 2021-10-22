package com.ninja.rmm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RMM_ROLE")
@Table(name = "RMM_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STATUS")
    private String status;
    /*
    @OneToMany(targetEntity = Customer.class, mappedBy = "role", fetch
            = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Customer> users;
     */
}
