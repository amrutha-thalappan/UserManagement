package com.usermanagement.utils;

/**
 *  A class to declare all the static constants
 *  which is used across the application
 */
public class Constants {

    //validation constants
    public static final String INPUT_NULL = "Invalid input data";
    public static final String USERNAME_NULL = "Username is empty";
    public static final String BIRTHDATE_NULL = "Birth date is empty";
    public static final String COUNTRY_NULL = "Country is empty";

    //Exception constants
    public static final String USERNAME_EXISTS = "Username already exists";
    public static final String USER_NOT_EXISTS = "User does not exist";
    public static final String INVALID_GENDER = "Invalid gender input";

    //error code constants
    public static final Integer USERNAME_NULL_CODE = 41701;
    public static final Integer BIRTHDATE_NULL_CODE = 41702;
    public static final Integer COUNTRY_NULL_CODE = 41703;
    public static final Integer INVALID_GENDER_CODE = 41704;
    public static final Integer INPUT_NULL_CODE = 41201;
    public static final Integer USERNAME_EXISTS_CODE = 40901;
    public static final Integer USER_NOT_EXISTS_CODE = 40401;
    public static final Integer RESTRICTED_AGE_LIMIT = 41705;
}
