package com.dgbuild.watermyplants.controllers;

import com.dgbuild.watermyplants.models.Plant;
import com.dgbuild.watermyplants.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @GetMapping(value = "/myplants",
                produces = "application/json")
    public ResponseEntity<?> listMyPlants(){
        List<Plant> myPlants = plantService.findMyPlants();
        return new ResponseEntity<>(myPlants, HttpStatus.OK);
    }

    // http://localhost:2019/plants/plant/{id}
    @GetMapping(value = "/plant/{plantId}",
            produces = "application/json")
    public ResponseEntity<?> findPlantById(@PathVariable long plantId){
        Plant getPlant = plantService.findById(plantId);
        return new ResponseEntity<>(getPlant, HttpStatus.OK);
    }

    // Post http://localhost:2019/plants/water/{id}
    @PostMapping(value = "/water/{plantId}",
                produces = "application/json")
    public ResponseEntity<?> waterPlant(@PathVariable long plantId){
        String newWaterDate = plantService.waterPlant(plantId);
        return new ResponseEntity<>(newWaterDate, HttpStatus.OK);
    }

    // POST http://localhost:2019/plants/plant
    @PostMapping(value = "/plant",
                consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<?> addNewPlant(@Valid @RequestBody Plant newPlant){
        plantService.save(newPlant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // POST http://localhost:2019/plants/myplants/add
    @PostMapping(value = "/myplants/add",
                consumes = "application/json")
    public ResponseEntity<?> addMyPlant(@Valid @RequestBody Plant newPlant){
        newPlant.setPlantid(0);
        plantService.addMyPlant(newPlant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // POST http://localhost:2019/plants/myplants/{id}
    @PutMapping(value = "/myplants/{plantId}",
            consumes = "application/json")
    public ResponseEntity<?> updateMyPlant(@Valid @RequestBody Plant newPlant){
        plantService.addMyPlant(newPlant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/plants/plant/{id}
    @PutMapping(value = "/plant/{id}",
                consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<?> updateWholePlant(@PathVariable long id,@Valid @RequestBody Plant updatePlant){
        plantService.update(updatePlant, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PATCH http://localhost:2019/plants/plant/{id}
    @PatchMapping(value = "/plant/{plantId}",
                consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<?> updatePlant(@PathVariable long plantId, @RequestBody Plant updatePlant){
        plantService.update(updatePlant, plantId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // DELETE http://localhost:2019/plants/plant/{id}
    @DeleteMapping(value = "/plant/{plantId}")
    public ResponseEntity<?> deletePlant(@PathVariable long plantId){
        plantService.delete(plantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
