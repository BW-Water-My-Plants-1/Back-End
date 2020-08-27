package com.dgbuild.watermyplants;

import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.models.Role;
import com.dgbuild.watermyplants.models.User;
import com.dgbuild.watermyplants.models.UserRoles;
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

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

//        User u1 = new User("admin", "password",
//                "admin@watermyplants.local", "(317)867-5309");
        User u1 = new User();

        u1.setUsername("admin");
        u1.setPassword("password");
        u1.setEmail("admin@watermyplants.local");
        u1.setPhone("(317)867-5309");

        u1 = userService.save(u1);

        u1.getRoles()
                .add(new UserRoles(u1, r1));

        Plant p1 = new Plant("Habanero", "Pepper", "8-25-2020",
                            "Daily", u1);
        p1.setPlantid(0);

        p1 = plantService.save(p1);

        u1.getPlants().add(p1);

        User u2 = new User();

        u2.setUsername("user");
        u2.setPassword("password");
        u2.setEmail("user@watermyplants.local");
        u2.setPhone("(574)123-4567");

        u2 = userService.save(u2);

        u2.getRoles()
                .add(new UserRoles(u2, r2));

        Plant p2 = new Plant("Roma", "Tomato","8-25-2020",
                "Daily", u2);
        p2.setPlantid(0);
        p2 = plantService.save(p2);

        Plant p3 = new Plant("Honeydew", "Melon","8-25-2020",
                "Daily", u2);
        p3.setPlantid(0);
        p3 = plantService.save(p3);

        Plant p4 = new Plant("Straight 8", "Cucumber","8-25-2020",
                "Daily", u2);
        p4.setPlantid(0);
        p4 = plantService.save(p4);

        u2.getPlants().add(p2);
        u2.getPlants().add(p3);
        u2.getPlants().add(p4);
    }
}
