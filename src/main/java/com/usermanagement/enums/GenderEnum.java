package com.usermanagement.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.usermanagement.exception.CustomException;
import com.usermanagement.utils.Constants;
import org.springframework.http.HttpStatus;


/**
 * Enum class to define the possible values of gender
 */
public enum GenderEnum {

    MALE("male"),

    FEMALE("female"),

    OTHER("other");

    private final String value;

    GenderEnum(String value) {
        this.value = value;
    }

    /**
     * This method is to give the enum property corresponds to the given value
     *
     * @param text given gender input
     * @return Enum property corresponds to the input string
     * @throws CustomException Error which specifies invalid gender input
     */
    @JsonCreator
    public static GenderEnum fromValue(String text) throws CustomException {
        for (GenderEnum b : GenderEnum.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new CustomException(HttpStatus.EXPECTATION_FAILED, Constants.INVALID_GENDER_CODE, Constants.INVALID_GENDER);
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}
