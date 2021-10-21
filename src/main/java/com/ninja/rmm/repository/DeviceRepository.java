package com.ninja.rmm.repository;

import com.ninja.rmm.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query(value = "SELECT * FROM RMM_DEVICE WHERE CUSTOMER_ID = ?1 AND STATUS='A'", nativeQuery = true)
    List<Device> findDevicesByCustomer(Long customerId);

    @Query(value = "SELECT ID FROM RMM_DEVICE WHERE CUSTOMER_ID = ?1 AND SYSTEM_NAME= ?2 AND TYPE= ?3", nativeQuery = true)
    Long existById(Long customerId, String systemName, String type);

    @Query(value = "UPDATE RMM_DEVICE SET SYSTEM_NAME= ?1, TYPE= ?2 WHERE CUSTOMER_ID = ?3 AND SYSTEM_NAME= ?4 AND TYPE= ?5", nativeQuery = true)
    @Modifying
    void updateCustomerDevice(String newSystemName, String newType, Long customerId, String currentSystemName, String currentType);

    @Query(value = "DELETE RMM_DEVICE WHERE CUSTOMER_ID = ?1 AND SYSTEM_NAME= ?2 AND TYPE= ?3", nativeQuery = true)
    @Modifying
    void deleteCustomerDevice(Long customerId, String systemName, String type);


}
