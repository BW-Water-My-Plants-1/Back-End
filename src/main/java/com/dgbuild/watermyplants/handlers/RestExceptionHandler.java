package com.dgbuild.watermyplants.handlers;

import com.dgbuild.watermyplants.exceptions.AccessDeniedException;
import com.dgbuild.watermyplants.exceptions.ResourceFoundException;
import com.dgbuild.watermyplants.exceptions.ResourceNotFoundException;
import com.dgbuild.watermyplants.models.ErrorDetail;
import com.dgbuild.watermyplants.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private HelperFunctions helperFunctions;

    public RestExceptionHandler() {
        super();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass()
                .getName());
        errorDetail.setErrors(helperFunctions.getConstraintViolation(rnfe));

        return new ResponseEntity<>(errorDetail,
                null,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceFoundException.class)
    public ResponseEntity<?> handleResourceFoundException(ResourceFoundException rfe){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Unexpected Resource");
        errorDetail.setDetail(rfe.getMessage());
        errorDetail.setDeveloperMessage(rfe.getClass().getName());
        errorDetail.setErrors(helperFunctions.getConstraintViolation(rfe));

        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ade){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date());
        errorDetail.setStatus(HttpStatus.FORBIDDEN.value());
        errorDetail.setTitle("Action Forbidden");
        errorDetail.setDetail(ade.getMessage());
        errorDetail.setDeveloperMessage(ade.getClass().getName());
        errorDetail.setErrors(helperFunctions.getConstraintViolation(ade));

        return new ResponseEntity<>(errorDetail, null, HttpStatus.FORBIDDEN);
    }
}
