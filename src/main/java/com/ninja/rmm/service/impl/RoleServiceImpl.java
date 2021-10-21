package com.ninja.rmm.service.impl;

import com.ninja.rmm.model.Role;
import com.ninja.rmm.pojo.role.RoleManagement;
import com.ninja.rmm.repository.RoleRepository;
import com.ninja.rmm.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public String save(RoleManagement roleManagement) {

        Role role = new Role();

        if (roleManagement.getName() != null)
            role.setName(roleManagement.getName());

        if (checkRoleExist(role))
            return "Role exist already!";

        role.setStatus(roleManagement.getStatus());

        try {
            roleRepository.save(role);
        } catch (Exception e) {
            log.error("A problem occurred while trying to add a new role: " + e.getMessage());
            e.printStackTrace();
            return "Could not add the role " + role.getName();
        }

        return "Role added successfully!";
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            roleRepository.deleteById(id);
            jsonObject.put("message", "Role deleted successfully!");
        } catch (Exception e) {
            log.error("A problem occurred while trying to delete role: " + e.getMessage());
        }

        return jsonObject.toString();
    }

    private boolean checkRoleExist(Role role) {

        boolean roleExist=false;

        int response = roleRepository.roleExist(role.getName());

        if (response > 0)
            roleExist = true;

        return roleExist;

    }
}
