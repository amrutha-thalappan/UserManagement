package com.usermanagement.api;

import com.usermanagement.dto.ErrorResponse;
import com.usermanagement.dto.SuccessResponse;
import com.usermanagement.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.exception.CustomException;
import com.usermanagement.service.UserService;
import com.usermanagement.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * This controller class provides implementations of all the methods declared in the UserApi interface
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-30T23:10:58.140Z[GMT]")
@RestController
@Api(tags = "User")
public class UserApiController implements UserApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    /**
     * Controller layer POST API implementation for user registration
     * @param userDto UserDto object to the Api, not null
     * @return Response entity to return any object type as a response.
     * Returns success or error response in the corresponding cases
     */
    public ResponseEntity<?> addUser(@Parameter(in = ParameterIn.DEFAULT, description = "User object having necessary details that needs to be saved", required=true, schema=@Schema()) @Valid @RequestBody UserDto userDto) {
        if(userDto == null){
            return new ResponseEntity<>(new ErrorResponse("User Object null", "User object cannot be null", Constants.INPUT_NULL, Constants.INPUT_NULL_CODE), HttpStatus.PRECONDITION_FAILED);
        }
        try {
            userService.saveUser(userDto);
            return new ResponseEntity<>(new SuccessResponse(true, "User details saved successfully", null, 200),HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new ErrorResponse("Save failed", "User object cannot be saved", e.getErrorMessage(), e.getErrorCode()), e.getStatusCode());
        }
        }

    /**
     * Controller layer GET API implementation to retrieve user by username
     * @param username username of the user whose details has to be retrived
     * @return Response entity to return any object type as a response.
     * Returns success response with userDto object if user exists
     * Otherwise return error response
     */
    public ResponseEntity<?> getUserByUserName(@Parameter(in = ParameterIn.PATH, description = "The name that needs to be used to fetch the user details", required=true, schema=@Schema()) @PathVariable("username") String username) {
        try {
            UserDto userDto = userService.findByUsername(username);
            return new ResponseEntity<>(new SuccessResponse(true, "User details fetched successfully", userDto, 200),HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new ErrorResponse("User fetch failed", "Could not fetch user details", e.getErrorMessage(), e.getErrorCode()), e.getStatusCode());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        List<ErrorResponse> responses = new ArrayList<>();
        for(ObjectError error: objectErrors){
            if(error.getArguments().length > 0){
                String field = ((DefaultMessageSourceResolvable)error.getArguments()[0]).getDefaultMessage();
                Integer errorCode = userService.getValidationErrorCode(field, error.getCode());
                if(errorCode != null) {
                    ErrorResponse errorResponse = new ErrorResponse("Precondition failed", "Unable to process the request",error.getDefaultMessage(), errorCode);
                    responses.add(errorResponse);
                }
            }
        }
        if(!responses.isEmpty()){
            return new ResponseEntity<>(responses, HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

}
