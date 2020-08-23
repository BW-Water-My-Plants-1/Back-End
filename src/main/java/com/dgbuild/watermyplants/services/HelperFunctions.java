package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.models.ValidationError;

import java.util.List;

public interface HelperFunctions {
    List<ValidationError> getConstraintViolation(Throwable cause);
}
