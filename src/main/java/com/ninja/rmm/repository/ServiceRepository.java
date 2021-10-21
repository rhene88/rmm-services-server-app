package com.ninja.rmm.repository;

import com.ninja.rmm.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query(value = "SELECT ID FROM RMM_SERVICE WHERE NAME = ?1 AND DEVICE_TYPE= ?2", nativeQuery = true)
    Long existByName(String serviceName, String serviceType);


}
