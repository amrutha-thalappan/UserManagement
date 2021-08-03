package com.usermanagement.validation;

import com.usermanagement.utils.Utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Defines the logic to validate age limit constraint for a given birthdate object
 */
public class AgeValidator implements ConstraintValidator<AgeLimit, Date> {

    @Override
    public void initialize(AgeLimit ageLimit) {
    }

    /**
     * Overridden method which checks that the user with given birthdate is adult or not
     *
     * @param birthDate date of birth of the registering user, not null
     * @param context   Provides contextual data and operation when applying a given constraint validator
     * @return True if the user is adult, otherwise vise versa
     */
    @Override
    public boolean isValid(Date birthDate, ConstraintValidatorContext context) {
        return Utilities.isAdult(birthDate);
    }
}
