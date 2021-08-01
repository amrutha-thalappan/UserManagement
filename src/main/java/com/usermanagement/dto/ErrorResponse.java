package com.usermanagement.dto;

import java.util.List;

public class ErrorResponse {

    private String summary;

    private String description;

    private String errorCode;

    private Integer statusCode;

    public ErrorResponse(String summary, String description, String errorCode, Integer statusCode) {
        this.summary = summary;
        this.description = description;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
