package com.dgbuild.watermyplants.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message){
        super("Error from WaterMyPlants application: " + message);
    }
}
