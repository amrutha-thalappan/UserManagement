package com.usermanagement.service;

import com.usermanagement.dto.UserDto;
import com.usermanagement.exception.CustomException;

/**
 * An interface containing service layer method declarations for User APIs
 */
public interface UserService {
    /**
     * Service layer method declaration for User registration.
     * In this method it validates user object whether it has values for all the mandatory fields or not
     * Then it checks whether the user already exists with given username in the input object
     * Then the userDto object will be mapped to User object
     * and saved to the database if all the validations are success
     * @param userDto The Dto object from the API, not null
     * @throws CustomException Error which specifies exact reason for the validation failure
     */
    public void saveUser(UserDto userDto) throws CustomException;

    /**
     * Service layer method declaration for retrieving user details by username
     * Initially it checks the username is null or not.
     * It retrieves the user object, map to userDto object and return to the API if the username is not null,
     * otherwise it throws custom exception with corresponding error message
     * @param username Username of the user whose details have to be retrieved
     * @return A valid UserDto object which contain all the details of the user
     * @throws CustomException Error which specifies exact reason for the validation failure
     */
    public UserDto findByUsername(String username) throws CustomException;


    /**
     * This method defines the custom error code for the validation of given input field
     * @param field input field in the UserDto object
     * @param errorType Reason for the validation failure such as AgeLimi, NotNull etc.
     * @return Gives corresponding custom validation error code of the given field
     */
    public Integer getValidationErrorCode(String field, String errorType);
}
