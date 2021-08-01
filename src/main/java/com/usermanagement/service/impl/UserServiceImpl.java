package com.usermanagement.service.impl;

import com.usermanagement.dto.ErrorResponse;
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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(UserDto userDto) throws CustomException {
        if(userDto.getUsername() == null || userDto.getUsername().isEmpty()){
            throw new CustomException(HttpStatus.EXPECTATION_FAILED, 41701, Constants.USERNAME_NULL);
        }if(userDto.getBirthDate() == null){
            throw new CustomException(HttpStatus.EXPECTATION_FAILED, 41702, Constants.BIRTHDATE_NULL);
        }if(userDto.getCountry() == null || userDto.getCountry().isEmpty()){
            throw new CustomException(HttpStatus.EXPECTATION_FAILED, 41703, Constants.COUNTRY_NULL);
        }
        if(userRepository.findByUsername(userDto.getUsername()) != null){
            throw new CustomException(HttpStatus.CONFLICT, 40901, Constants.USERNAME_EXISTS);
        }else {
            User user = mapUserDto(userDto);
            userRepository.save(user);
        }
    }

    @Override
    public UserDto findByUsername(String username) throws CustomException {
        if(username == null || username.isEmpty()){
            throw new CustomException(HttpStatus.EXPECTATION_FAILED, 41701, Constants.USERNAME_NULL);
        }
        User user = userRepository.findByUsername(username);
        if(user != null){
            UserDto userDto = mapUser(user);
            return userDto;
        }else{
            throw new CustomException(HttpStatus.NOT_FOUND, 40401, Constants.USER_NOT_EXISTS);
        }

    }

    private User mapUserDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setBirthDate(userDto.getBirthDate());
        user.setCountry(userDto.getCountry());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setGender(GenderEnum.fromValue(userDto.getGender()));
        return user;

    }

    private UserDto mapUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setCountry(user.getCountry());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setGender(user.getGender().toString());
        return userDto;
    }
}
