package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.models.User;
import com.dgbuild.watermyplants.models.UserRoles;
import com.dgbuild.watermyplants.repositories.PlantRepository;
import com.dgbuild.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User " + id + " Not Found"));
        userRepository.deleteById(id);
    }

    @Override
    public User save(User user) {
        User newUser = new User();

        if (user.getUserid() != 0){
            userRepository.findById(user.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User " + user.getUserid() + " Not Found"));
            newUser.setUserid(user.getUserid());
        }

        if (user.getEmail() != null){
            newUser.setEmail(user.getEmail());
        }

        if (user.getPassword() != null){
            newUser.setPassword(user.getPassword());
        }

        if (user.getPhone() != null){
            newUser.setPhone(user.getPhone());
        }

        if (user.getUsername() != null){
            newUser.setUsername(user.getUsername());
        }

        if (user.getPlants().size() > 0){
            newUser.getPlants().clear();
            for (Plant p: user.getPlants()){
                Plant newPlant = new Plant();

                p.setUser(newUser);

                if (plantRepository.findById(p.getPlantid()).isPresent()){
                    newPlant.setPlantid(p.getPlantid());
                }

                if (p.getSpecies() != null){
                    newPlant.setSpecies(p.getSpecies());
                }

                if (p.getNickname() != null){
                    newPlant.setNickname(p.getNickname());
                }

                if (p.hasValueForFrequency){
                    newPlant.setFrequency(p.getFrequency());
                }
                newPlant = plantRepository.save(newPlant);
                newUser.getPlants().add(newPlant);
            }
        }

        return userRepository.save(newUser);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }
}
