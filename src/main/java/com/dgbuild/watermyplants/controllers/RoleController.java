package com.dgbuild.watermyplants.controllers;

import com.dgbuild.watermyplants.models.Role;
import com.dgbuild.watermyplants.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    // http://localhost:2019/roles/roles
    @GetMapping(value = "/roles",
            produces = "application/json")
    public ResponseEntity<?> findAllRoles(){
        List<Role> allRoles = roleService.findAll();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    // http://localhost:2019/roles/role/{id}
    @GetMapping(value = "/role/{roleId}",
            produces = "application/json")
    public ResponseEntity<?> findRoleById(@PathVariable long roleId){
        Role getRole = roleService.findById(roleId);
        return new ResponseEntity<>(getRole, HttpStatus.OK);
    }

    // http://localhost:2019/roles/role/{id}

}