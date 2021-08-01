package com.usermanagement.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public static Boolean isAdult(Date birthDate){
        LocalDate today = LocalDate.now();
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer age = Period.between(date, today).getYears();
        return (age>=18?true:false);
    }

    public static Boolean isFrench(String country){
        Pattern r = Pattern.compile("(F|f)rance");
        Matcher m = r.matcher(country);
        return (m.find()?true:false);

    }

}
