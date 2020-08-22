package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "plantService")
public class PlantServiceImpl implements PlantService {
    @Autowired
    private PlantRepository plantRepository;

    @Override
    public void deleteAll() {
        plantRepository.deleteAll();
    }
}
