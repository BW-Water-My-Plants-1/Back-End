package com.dgbuild.watermyplants.controllers;

import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantController {
    @Autowired
    PlantService plantService;

    // http://localhost:2019/plants/plants
    @GetMapping(value = "/plants",
            produces = "application/json")
    public ResponseEntity<?> listAllPlants(){
        List<Plant> allPlants = plantService.findAll();
        return new ResponseEntity<>(allPlants, HttpStatus.OK);
    }

    // http://localhost:2019/plants/myPlants

    // http://localhost:2019/plants/plant/{id}
    @GetMapping(value = "/plant/{plantId}",
            produces = "application/json")
    public ResponseEntity<?> findPlantById(@PathVariable long plantId){
        Plant getPlant = plantService.findById(plantId);
        return new ResponseEntity<>(getPlant, HttpStatus.OK);
    }

    // POST http://localhost:2019/plants/plant

    // PUT http://localhost:2019/plants/plant/{id}

    // DELETE http://localhost:2019/plants/plant/{id}
}
