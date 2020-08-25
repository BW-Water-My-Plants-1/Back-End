package com.dgbuild.watermyplants.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super("Error from WaterMyPlants application " + message);
    }
}
