package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.exceptions.ResourceNotFoundException;
import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "plantService")
public class PlantServiceImpl implements PlantService {
    @Autowired
    private PlantRepository plantRepository;

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
        plantRepository.deleteById(id);
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

        return plantRepository.save(newPlant);
    }

    @Transactional
    @Override
    public Plant update(Plant plant, long id) {
        Plant newPlant = new Plant();

        plantRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Plant " + id + " Not Found"));
        newPlant.setPlantid(plant.getPlantid());

        Plant currentPlant = plantRepository.findById(id).get();

        if (plant.getNickname() != null) {
            newPlant.setNickname(plant.getNickname());
        }else{
            newPlant.setNickname(currentPlant.getNickname());
        }

        if (plant.getSpecies() != null) {
            newPlant.setSpecies(plant.getSpecies());
        }else{
            newPlant.setSpecies(currentPlant.getSpecies());
        }

        if (plant.getUser() != null) {
            newPlant.setUser(plant.getUser());
        }else{
            newPlant.setUser(currentPlant.getUser());
        }

        if (plant.hasValueForFrequency) {
            newPlant.setFrequency(plant.getFrequency());
        }else{
            newPlant.setFrequency(currentPlant.getFrequency());
        }

        return plantRepository.save(newPlant);
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
