package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.exceptions.AccessDeniedException;
import com.dgbuild.watermyplants.exceptions.ResourceNotFoundException;
import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.models.User;
import com.dgbuild.watermyplants.repositories.PlantRepository;
import com.dgbuild.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        if (!helperFunctions.isAuthorizedToMakeChange(userRepository.findById(id).get().getUsername())){
            throw new AccessDeniedException("You cannot delete other user's plants");
        }
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

        newPlant.setLastwatered(plant.getLastwatered());

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

        if (plant.getLastwatered() != null){
            currentPlant.setLastwatered(plant.getLastwatered());
        }

        if (plant.getFrequency() != null) {
            currentPlant.setFrequency(plant.getFrequency());
        }

        if (plant.getLastwatered() != null) {
            currentPlant.setLastwatered(plant.getLastwatered());
        }

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

    @Override
    public String waterPlant(long id) {
        Plant updatePlant = plantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Plant " + id + " Not Found"));

        if (!helperFunctions.isAuthorizedToMakeChange(updatePlant.getUser().getUsername())){
            throw new AccessDeniedException("You cannot water other user's plants");
        }

        SimpleDateFormat dateFor = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String stringDate = dateFor.format(date);

        updatePlant.setLastwatered(stringDate);

        return stringDate;
    }

    @Override
    public Plant addMyPlant(Plant myPlant) {
        Plant newPlant = new Plant();

        if (myPlant.getPlantid() != 0){
            plantRepository.findById(myPlant.getPlantid())
                    .orElseThrow(() -> new ResourceNotFoundException("Plant " + " Not Found"));
            newPlant.setPlantid(myPlant.getPlantid());
        }

        newPlant.setNickname(myPlant.getNickname());

        newPlant.setSpecies(myPlant.getSpecies());

        newPlant.setUser(userRepository.findByUsername(helperFunctions.getCurrentAuditor()));

        newPlant.setFrequency(myPlant.getFrequency());

        newPlant.setLastwatered(myPlant.getLastwatered());

        return plantRepository.save(newPlant);
    }
}
