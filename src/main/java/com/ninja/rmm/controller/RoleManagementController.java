package com.ninja.rmm.controller;

import com.ninja.rmm.model.Role;
import com.ninja.rmm.pojo.role.RoleManagement;
import com.ninja.rmm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("${swagger.service.contextPath}")
public class RoleManagementController {

    RoleService roleService;

    @Autowired
    public RoleManagementController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/role", produces = "application/json")
    public ResponseEntity<String> newRole(@RequestBody RoleManagement roleManagement) {

        String roleResponse = roleService.save(roleManagement);

        return new ResponseEntity<>(roleResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/role", produces = "application/json")
    public ResponseEntity<Collection<Role>> obtainRoles() {

        Collection<Role> roleList = roleService.findAll();

        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }
}
