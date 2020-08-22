package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }
}
