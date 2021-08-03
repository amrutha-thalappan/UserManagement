package com.usermanagement.exception;

import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class for exception handling
 * It has three custom attributes errorCode, statusCode and errorMessage to define and throw the exception
 */
public class CustomException extends Exception {

    /**
     * Defines the HTTP STATUS code corresponds to the validation failure
     */
    HttpStatus statusCode;

    /**
     * Defines the custom error code for validation failure
     * to identify exact root cause of the error
     * as there can be multiple errors for each HTTP STATUS
     * The first three digits identifies HTTP STATUS code
     * and this with next two digits identifies exact reason for the validation failure
     */
    Integer errorCode;

    /**
     * Defines the details of the validation failure which has defined as a constant
     */
    String errorMessage;

    public CustomException(HttpStatus statusCode, Integer errorCode, String errorMessage) {
        super();
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
