package com.usermanagement.validation;

import com.usermanagement.utils.Utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryValidator implements ConstraintValidator<Nationality, String> {
    @Override
    public void initialize(Nationality nationality) {
    }

    @Override
    public boolean isValid(String country, ConstraintValidatorContext context) {
        return Utilities.isFrench(country);
    }
}
