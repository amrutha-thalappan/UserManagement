package com.usermanagement.api;

import com.usermanagement.dto.ErrorResponse;
import com.usermanagement.dto.SuccessResponse;
import com.usermanagement.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * An interface containing controller layer method declarations for User APIs
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-30T23:10:58.140Z[GMT]")
@Validated
@RequestMapping(value = "/user-management/api/v1/user")
public interface UserApi {

    /**
     * Controller layer POST API declaration for user registration
     * which uses content type 'application/json'
     *
     * @param userDto UserDto object to the Api, not null
     * @return Response entity to return any object type as a response.
     * Returns success or error response in the corresponding cases
     */
    @Operation(summary = "Add a new user", description = "", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SuccessResponse.class)))),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))),
            @ApiResponse(responseCode = "417", description = "Expectation failed", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))),
            @ApiResponse(responseCode = "412", description = "Precondition failed", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))})
    @PostMapping(value = "/",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<?> addUser(@Parameter(in = ParameterIn.DEFAULT, description = "User object having necessary details that needs to be saved", required = true, schema = @Schema()) @Valid @RequestBody UserDto userDto);


    /**
     * Controller layer GET API declaration to retrieve user by username
     * which uses content type 'application/json'
     *
     * @param username username of the user whose details has to be retrived
     * @return Response entity to return any object type as a response.
     * Returns success response with userDto object if user exists
     * Otherwise return error response
     */
    @Operation(summary = "Get user by user name", description = "", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SuccessResponse.class)))),
            @ApiResponse(responseCode = "404", description = "The specified resource was not found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))})
    @GetMapping(value = "/{username}",
            produces = {"application/json"})
    ResponseEntity<?> getUserByUserName(@Parameter(in = ParameterIn.PATH, description = "The name that needs to be used to fetch the user details", required = true, schema = @Schema()) @PathVariable("username") String username);

}

