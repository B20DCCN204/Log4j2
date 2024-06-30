package com.example.demoLog4j.service;

import com.example.demoLog4j.dto.UserDto;
import com.example.demoLog4j.dto.response.UserResponse;

public interface UserService {
    UserResponse saveUser(UserDto userDto);
    UserDto findByUsername(String Username);
    Boolean checkPassword (UserDto userDto, String rawPassword);
    UserResponse getUserById(Long id);

    void deleteUser(Long id);
}
