package com.ninja.rmm.repository;

import com.ninja.rmm.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT COUNT(*) FROM RMM_ROLE WHERE NAME = ?1 AND STATUS='A'", nativeQuery = true)
    int roleExist(String roleName);
}
