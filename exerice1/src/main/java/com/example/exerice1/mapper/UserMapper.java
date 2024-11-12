package com.example.exerice1.mapper;

import com.example.exerice1.dto.UserDto;
import com.example.exerice1.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
