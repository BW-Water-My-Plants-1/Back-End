package com.dgbuild.watermyplants.exceptions;

public class ResourceFoundException extends RuntimeException {
    public ResourceFoundException(String message){
        super("Error from WaterMyPlants application " + message);
    }
}
