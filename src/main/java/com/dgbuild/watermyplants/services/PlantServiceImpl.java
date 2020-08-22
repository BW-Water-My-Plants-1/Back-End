package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "plantService")
public class PlantServiceImpl implements PlantService {
    @Autowired
    private PlantRepository plantRepository;

    @Override
    public void deleteAll() {
        plantRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        plantRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Plant "+ id + " Not Found"));
        plantRepository.deleteById(id);
    }

    @Override
    public Plant save(Plant plant) {
        Plant newPlant = new Plant();

        if (plant.getPlantid() != 0){
            plantRepository.findById(plant.getPlantid())
                    .orElseThrow(()->new EntityNotFoundException("Plant " + plant.getPlantid() + " Not Found"));
            newPlant.setPlantid(plant.getPlantid());
        }

        if (plant.getNickname() != null) {
            newPlant.setNickname(plant.getNickname());
            return null;
        }

        if (plant.getSpecies() != null) {
            newPlant.setSpecies(plant.getSpecies());
        }

        if (plant.getUser() != null) {
            newPlant.setUser(plant.getUser());
        }

        if (plant.hasValueForFrequency) {
            newPlant.setFrequency(plant.getFrequency());
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
                .orElseThrow(()-> new EntityNotFoundException("Plant " + id + " Not Found"));
        return plantRepository.findById(id).get();
    }
}
