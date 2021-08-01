package com.usermanagement.api;

import com.usermanagement.dto.ErrorResponse;
import com.usermanagement.dto.SuccessResponse;
import com.usermanagement.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.exception.CustomException;
import com.usermanagement.service.UserService;
import com.usermanagement.utils.Constants;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> addUser(@Parameter(in = ParameterIn.DEFAULT, description = "User object having necessary details that needs to be saved", required=true, schema=@Schema()) @Valid @RequestBody UserDto userDto) {
        if(userDto == null){
            return new ResponseEntity<>(new ErrorResponse("User Object null", "User object cannot be null", Constants.INPUT_NULL, 412), HttpStatus.PRECONDITION_FAILED);
        }
        try {
            userService.saveUser(userDto);
            return new ResponseEntity<>(new SuccessResponse(true, "User details saved successfully", null, 200),HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new ErrorResponse("Save failed", "User object cannot be saved", e.getErrorMessage(), e.getStatusCode()), e.getErrorCode());
        }
        }

    public ResponseEntity<?> getUserByUserName(@Parameter(in = ParameterIn.PATH, description = "The name that needs to be used to fetch the user details", required=true, schema=@Schema()) @PathVariable("username") String username) {
        try {
            UserDto userDto = userService.findByUsername(username);
            return new ResponseEntity<>(new SuccessResponse(true, "User details fetched successfully", userDto, 200),HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new ErrorResponse("User fetch failed", "Could not fetch user details", e.getErrorMessage(), e.getStatusCode()), e.getErrorCode());
        }
    }

}
