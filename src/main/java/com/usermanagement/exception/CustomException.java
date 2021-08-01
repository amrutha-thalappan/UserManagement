package com.usermanagement.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception{

    HttpStatus errorCode;
    Integer statusCode;
    String errorMessage;

    public CustomException(HttpStatus errorCode, Integer statusCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
