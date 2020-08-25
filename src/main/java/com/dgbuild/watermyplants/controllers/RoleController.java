package com.dgbuild.watermyplants.controllers;

import com.dgbuild.watermyplants.models.Role;
import com.dgbuild.watermyplants.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    // POST http://localhost:2019/roles/role
    @PostMapping(value = "/role",
                consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<?> addNewRole(@Valid @RequestBody Role newRole){
        newRole.setRoleid(0);
        newRole = roleService.save(newRole);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/roles/role/{id}
    @PutMapping(value = "/role/{roleId}",
                consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<?> updateRole(@PathVariable long roleId, @RequestBody Role updateRole){
        updateRole.setRoleid(roleId);
        updateRole = roleService.save(updateRole);
        return new ResponseEntity<>(updateRole, HttpStatus.CREATED);
    }

    // DELETE http://localhost:2019/roles/role/{id}
    @DeleteMapping(value = "/role/{roleId}",
                    consumes = "application/json",
                    produces = "application/json")
    public ResponseEntity<?> deleteRole(@PathVariable long roleId){
        roleService.delete(roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}