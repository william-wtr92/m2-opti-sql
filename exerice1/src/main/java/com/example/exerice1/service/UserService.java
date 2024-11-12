package com.example.exerice1.service;

import com.example.exerice1.dto.UserDto;

public interface UserService {
    void createUser(UserDto userDto);
    UserDto getUserById(Long id);
    void updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
