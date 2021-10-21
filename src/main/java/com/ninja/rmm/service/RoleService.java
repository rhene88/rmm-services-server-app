package com.ninja.rmm.service;

import com.ninja.rmm.model.Role;
import com.ninja.rmm.pojo.role.RoleManagement;

import java.util.Collection;
import java.util.Optional;

public interface RoleService {

    Collection<Role> findAll();

    Optional<Role> findById(Long id);

    String save(RoleManagement roleManagement);

    String deleteById(Long id);
}
