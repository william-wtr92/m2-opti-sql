package com.example.exerice1.service.impl;

import com.example.exerice1.dto.UserDto;
import com.example.exerice1.entity.User;
import com.example.exerice1.mapper.UserMapper;
import com.example.exerice1.repository.UserRepository;
import com.example.exerice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);

        userRepository.save(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        return userMapper.toDto(user);
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return;
        }

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
