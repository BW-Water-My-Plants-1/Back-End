package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.models.ValidationError;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions{
    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause) {
        while ((cause != null) && !(cause instanceof ConstraintViolationException)){
            cause = cause.getCause();
        }

        List<ValidationError> listVE = new ArrayList<>();

        if (cause != null){
            ConstraintViolationException ex = (ConstraintViolationException) cause;
            for (ConstraintViolation cv : ex.getConstraintViolations()){
                ValidationError newVe = new ValidationError();
                newVe.setCode(cv.getInvalidValue().toString());
                newVe.setMessage(cv.getMessage());
                listVE.add(newVe);
            }
        }
        return listVE;
    }

    @Override
    public String getCurrentAuditor() {
        String username;
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();

        if (authenticatedUser != null){
            username = authenticatedUser.getName();
        } else {
            username = "SYSTEM";
        }
        return username;
    }

    @Override
    public boolean isAuthorizedToMakeChange(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return username.equalsIgnoreCase(authentication.getName().toLowerCase()) || authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
