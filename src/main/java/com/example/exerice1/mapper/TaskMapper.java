package com.example.exerice1.mapper;

import com.example.exerice1.dto.TaskDto;
import com.example.exerice1.entity.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);
}
