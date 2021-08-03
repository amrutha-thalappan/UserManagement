package com.usermanagement.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Generic API response class for all successful execution of functionalities
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-30T23:10:58.140Z[GMT]")
public class SuccessResponse {

  @JsonProperty("success")
  private Boolean success;

  @JsonProperty("message")
  private String message;

  @JsonProperty("data")
  private Object data;

  @JsonProperty("code")
  private int code;

  public SuccessResponse() {
  }

  public SuccessResponse(Boolean success, String message, Object data, int code) {
    this.success = success;
    this.message = message;
    this.data = data;
    this.code = code;
  }

  @Schema(description = "")
  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  @Schema(description = "")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Schema(description = "")
  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  @Schema(description = "")
  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return "SuccessResponse{" +
            "success=" + success +
            ", messages='" + message + '\'' +
            ", data=" + data +
            ", code=" + code +
            '}';
  }
}
