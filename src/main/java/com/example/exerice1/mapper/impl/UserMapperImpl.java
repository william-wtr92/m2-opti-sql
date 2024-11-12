package com.example.exerice1.mapper.impl;

import com.example.exerice1.mapper.UserMapper;
import com.example.exerice1.dto.ProjectDto;
import com.example.exerice1.dto.UserDto;
import com.example.exerice1.entity.Project;
import com.example.exerice1.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        if (user.getProjects() != null) {
            userDto.setProjects(
                    user.getProjects().stream()
                            .map(project -> new ProjectDto(project.getId(), project.getName(), project.getDescription(), null, null))
                            .collect(Collectors.toList())
            );
        }

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        if (userDto.getProjects() != null) {
            user.setProjects(
                    userDto.getProjects().stream()
                            .map(projectDto -> new Project(projectDto.getId(), projectDto.getName(), projectDto.getDescription(), null, null))
                            .collect(Collectors.toList())
            );
        }

        return user;
    }
}
