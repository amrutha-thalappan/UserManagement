package com.usermanagement.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Generic API response class for all successful execution of functionalities
 */
public class SuccessResponse {
  private Boolean success;

  private String messages;

  private Object data;

  private int code;

  public SuccessResponse() {
  }

  public SuccessResponse(Boolean success, String messages, Object data, int code) {
    this.success = success;
    this.messages = messages;
    this.data = data;
    this.code = code;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessages() {
    return messages;
  }

  public void setMessages(String messages) {
    this.messages = messages;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

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
            ", messages='" + messages + '\'' +
            ", data=" + data +
            ", code=" + code +
            '}';
  }
}
