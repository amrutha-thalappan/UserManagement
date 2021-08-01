package com.usermanagement.service;

import com.usermanagement.dto.UserDto;
import com.usermanagement.exception.CustomException;

public interface UserService {
    public void saveUser(UserDto userDto) throws CustomException;

    public UserDto findByUsername(String username) throws CustomException;
}
