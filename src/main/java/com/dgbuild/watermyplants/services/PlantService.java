package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.models.Plant;

import java.util.List;

public interface PlantService {
    void deleteAll();

    void delete(long id);

    Plant save(Plant plant);

    List<Plant> findAll();

    Plant findById(long id);
}
