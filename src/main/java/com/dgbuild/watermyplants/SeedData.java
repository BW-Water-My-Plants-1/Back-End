package com.dgbuild.watermyplants;

import com.dgbuild.watermyplants.models.Role;
import com.dgbuild.watermyplants.services.PlantService;
import com.dgbuild.watermyplants.services.RoleService;
import com.dgbuild.watermyplants.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    PlantService plantService;


    @Override
    public void run(String[] args) throws Exception {
        roleService.deleteAll();

        userService.deleteAll();

        plantService.deleteAll();
    }
}
