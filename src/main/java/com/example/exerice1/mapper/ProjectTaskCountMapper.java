package com.example.exerice1.mapper;

import com.example.exerice1.dto.ProjectTaskCountDto;
import com.example.exerice1.entity.ProjectTaskCount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectTaskCountMapper {
    @Mapping(source = "projectId", target = "projectId")
    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "taskCount", target = "taskCount")
    ProjectTaskCountDto toDto(ProjectTaskCount projectTaskCount);

    @Mapping(source = "projectId", target = "projectId")
    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "taskCount", target = "taskCount")
    ProjectTaskCount toEntity(ProjectTaskCountDto projectTaskCountDto);
}

