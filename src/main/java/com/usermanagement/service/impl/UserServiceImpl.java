package com.usermanagement.service.impl;

import com.usermanagement.dto.UserDto;
import com.usermanagement.enums.GenderEnum;
import com.usermanagement.exception.CustomException;
import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserService;
import com.usermanagement.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * This class provides implementations of all the methods declared in the UserService interface
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(UserDto userDto) throws CustomException {
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            throw new CustomException(HttpStatus.EXPECTATION_FAILED, Constants.USERNAME_NULL_CODE, Constants.USERNAME_NULL);
        }
        if (userDto.getBirthDate() == null) {
            throw new CustomException(HttpStatus.EXPECTATION_FAILED, Constants.BIRTHDATE_NULL_CODE, Constants.BIRTHDATE_NULL);
        }
        if (userDto.getCountry() == null || userDto.getCountry().isEmpty()) {
            throw new CustomException(HttpStatus.EXPECTATION_FAILED, Constants.COUNTRY_NULL_CODE, Constants.COUNTRY_NULL);
        }
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new CustomException(HttpStatus.CONFLICT, Constants.USERNAME_EXISTS_CODE, Constants.USERNAME_EXISTS);
        } else {
            User user = mapUserDto(userDto);
            userRepository.save(user);
        }
    }

    @Override
    public UserDto findByUsername(String username) throws CustomException {
        if ( username == null || username.trim().isEmpty()) {
            throw new CustomException(HttpStatus.EXPECTATION_FAILED, Constants.USERNAME_NULL_CODE, Constants.USERNAME_NULL);
        }
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return mapUser(user);
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, Constants.USER_NOT_EXISTS_CODE, Constants.USER_NOT_EXISTS);
        }
    }

    @Override
    public Integer getValidationErrorCode(String field, String errorType) {
        if (field.equalsIgnoreCase("birthDate")) {
            if (errorType.equalsIgnoreCase("AgeLimit")) {
                return Constants.RESTRICTED_AGE_LIMIT;
            } else {
                return Constants.BIRTHDATE_NULL_CODE;
            }
        } else if (field.equalsIgnoreCase(("username"))) {
            return Constants.USERNAME_NULL_CODE;
        } else if (field.equalsIgnoreCase("country")) {
            return Constants.COUNTRY_NULL_CODE;
        }
        return null;
    }

    private User mapUserDto(UserDto userDto) throws CustomException {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setBirthDate(userDto.getBirthDate());
        user.setCountry(userDto.getCountry());
        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        if (userDto.getGender() != null) {
            user.setGender(GenderEnum.fromValue(userDto.getGender()));
        }
        return user;

    }

    private UserDto mapUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setCountry(user.getCountry());
        if (user.getPhoneNumber() != null) {
            userDto.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getGender() != null) {
            userDto.setGender(user.getGender().toString());
        }
        return userDto;
    }
}
