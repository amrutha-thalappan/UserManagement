package com.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Generic API response class for all failed execution of functionalities
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-30T23:10:58.140Z[GMT]")
public class ErrorResponse {

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("description")
    private String description;

    @JsonProperty("message")
    private String message;

    @JsonProperty("errorCode")
    private Integer errorCode;

    public ErrorResponse(String summary, String description, String message, Integer errorCode) {
        this.summary = summary;
        this.description = description;
        this.message = message;
        this.errorCode = errorCode;
    }

    @Schema(description = "")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Schema(description = "")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Schema(description = "")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Schema(description = "")
    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", errorCode='" + message + '\'' +
                ", statusCode=" + errorCode +
                '}';
    }
}
