package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.exceptions.ResourceNotFoundException;
import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.models.User;
import com.dgbuild.watermyplants.repositories.PlantRepository;
import com.dgbuild.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "plantService")
public class PlantServiceImpl implements PlantService {
    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private HelperFunctions helperFunctions;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void deleteAll() {
        plantRepository.deleteAll();
    }

    @Transactional
    @Override
    public void delete(long id) {
        plantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Plant "+ id + " Not Found"));
//        if (helperFunctions.getCurrentAuditor() != "SYSTEM" || !helperFunctions.isAuthorizedToMakeChange(plantRepository.findById(id).get().getUser().getUsername())){
//            throw new OAuth2AccessDeniedException("You cannot delete plants that aren't yours");
//        }
        plantRepository.deleteById(id);
    }

    @Override
    public List<Plant> findMyPlants() {
        List<Plant> myPlants = new ArrayList<>();
        User currentUser = userRepository.findByUsername(helperFunctions.getCurrentAuditor());
        currentUser.getPlants().iterator().forEachRemaining(myPlants::add);
        return myPlants;
    }

    @Transactional
    @Override
    public Plant save(Plant plant) {
        Plant newPlant = new Plant();

        if (plant.getPlantid() != 0){
            plantRepository.findById(plant.getPlantid())
                    .orElseThrow(()->new ResourceNotFoundException("Plant " + plant.getPlantid() + " Not Found"));
            newPlant.setPlantid(plant.getPlantid());
        }

        newPlant.setNickname(plant.getNickname());

        newPlant.setSpecies(plant.getSpecies());

        newPlant.setUser(plant.getUser());

        newPlant.setFrequency(plant.getFrequency());

//        if (helperFunctions.getCurrentAuditor() != "SYSTEM" || !helperFunctions.isAuthorizedToMakeChange(newPlant.getUser().getUsername())){
//            throw new OAuth2AccessDeniedException("You cannot change plants that aren't yours");
//        }

        return plantRepository.save(newPlant);
    }

    @Transactional
    @Override
    public Plant update(Plant plant, long id) {

        plantRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Plant " + id + " Not Found"));

        Plant currentPlant = plantRepository.findById(id).get();

        if (plant.getNickname() != null) {
            currentPlant.setNickname(plant.getNickname());
        }

        if (plant.getSpecies() != null) {
            currentPlant.setSpecies(plant.getSpecies());
        }

        if (plant.getUser() != null) {
            currentPlant.setUser(plant.getUser());
        }

        if (plant.hasValueForFrequency) {
            currentPlant.setFrequency(plant.getFrequency());
        }

//        if (helperFunctions.getCurrentAuditor() != "SYSTEM" || !helperFunctions.isAuthorizedToMakeChange(newPlant.getUser().getUsername())){
//            throw new OAuth2AccessDeniedException("You cannot change plants that aren't yours");
//        }

        return plantRepository.save(currentPlant);
    }

    @Override
    public List<Plant> findAll() {
        List<Plant> plantList = new ArrayList<>();
        plantRepository.findAll().iterator().forEachRemaining(plantList::add);
        return plantList;
    }

    @Override
    public Plant findById(long id) {
        plantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Plant " + id + " Not Found"));

        return plantRepository.findById(id).get();
    }
}
