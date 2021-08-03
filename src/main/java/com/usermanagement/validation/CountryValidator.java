package com.usermanagement.validation;

import com.usermanagement.utils.Utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Defines the logic to validate country of residence of registering user
 */
public class CountryValidator implements ConstraintValidator<Nationality, String> {
    @Override
    public void initialize(Nationality nationality) {
    }

    /**
     * Overridden method which checks that the user is French resident or not
     *
     * @param country country of the registering user,not null
     * @param context Provides contextual data and operation when applying a given constraint validator.
     * @return True if the user is French resident, otherwise vise versa
     */
    @Override
    public boolean isValid(String country, ConstraintValidatorContext context) {
        return Utilities.isFrench(country);
    }
}
