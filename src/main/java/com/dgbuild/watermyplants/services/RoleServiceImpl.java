package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.exceptions.ResourceFoundException;
import com.dgbuild.watermyplants.exceptions.ResourceNotFoundException;
import com.dgbuild.watermyplants.models.Role;
import com.dgbuild.watermyplants.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role " + id + " Not Found"));
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = new ArrayList<>();
        roleRepository.findAll().iterator().forEachRemaining(roleList::add);
        return roleList;
    }

    @Override
    public Role findById(long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role save(Role newRole) {
        if (newRole.getUsers().size() > 0){
            throw new ResourceFoundException("User Roles are not updated through Role.");
        }

        return  roleRepository.save(newRole);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
