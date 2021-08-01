package com.usermanagement.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.usermanagement.dto.UserDto;

public enum GenderEnum {

    MALE("male"),

    FEMALE("female"),

    OTHER("other");

    private String value;

    GenderEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static GenderEnum fromValue(String text) {
        for (GenderEnum b : GenderEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
