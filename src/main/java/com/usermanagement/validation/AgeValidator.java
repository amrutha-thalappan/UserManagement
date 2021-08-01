package com.usermanagement.validation;

import com.usermanagement.utils.Utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.*;
import java.util.Date;

public class AgeValidator implements ConstraintValidator<AgeLimit, Date> {

    @Override
    public void initialize(AgeLimit ageLimit) {
    }

    @Override
    public boolean isValid(Date birthDate, ConstraintValidatorContext context) {
        return Utilities.isAdult(birthDate);
    }
}
