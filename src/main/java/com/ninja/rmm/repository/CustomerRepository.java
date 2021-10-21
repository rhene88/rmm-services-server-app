package com.ninja.rmm.repository;


import com.ninja.rmm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "FROM RMM_CUSTOMER WHERE EMAIL = ?1 AND STATUS='A'", nativeQuery = true)
    Customer findByEmail(String email);
}
