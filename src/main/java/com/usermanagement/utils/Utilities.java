package com.usermanagement.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A helper class which contain static methods.
 * So that they can be reused across the application
 */
public class Utilities {

    /**
     * A method used to validate age of the registering user.
     * The user must be adult for the successful registration.
     * Otherwise, user will not be able to register to the User management system
     *
     * @param birthDate The date of birth of the registering user, Date class object, not null
     * @return A boolean value which defines whether the user is adult or not, not null
     */
    public static Boolean isAdult(Date birthDate) {
        LocalDate today = LocalDate.now();
        if (birthDate != null) {
            LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = Period.between(date, today).getYears();
            return (age >= 18);
        }
        return false;
    }


    /**
     * A method used to validate the country of the registering user
     * It checks whether the registering use is French resident or not
     *
     * @param country The country of the registering user, not null
     * @return A boolean value which defines whether the user is French resident or not, not null
     */
    public static Boolean isFrench(String country) {
        Pattern r = Pattern.compile("([Ff])rance");
        if (country != null && !country.isEmpty()) {
            Matcher m = r.matcher(country);
            return (m.find());
        }
        return false;
    }

}
