package com.ninja.rmm.repository;

import com.ninja.rmm.model.CustomerService;
import com.ninja.rmm.model.CustomerServiceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerService, CustomerServiceId> {

    @Query(value = "SELECT COUNT(*) FROM RMM_CUSTOMER_SERVICE WHERE CUSTOMER_ID = ?1 AND DEVICE_ID= ?2 AND SERVICE_ID= ?3 AND STATUS='A'", nativeQuery = true)
    int existById(Long customerId, Long deviceId, Long serviceId);

    @Query(value = "SELECT CUSTOMER_ID, DEVICE_ID, SERVICE_ID, STATUS, START_DATE, END_DATE FROM RMM_CUSTOMER_SERVICE WHERE CUSTOMER_ID = ?1 AND DEVICE_ID= ?2 AND STATUS='A'", nativeQuery = true)
    Collection<CustomerService> findAll(Long customerId, Long deviceId);


}
