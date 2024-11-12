package com.example.exerice1.mapper;

import com.example.exerice1.dto.ProjectDto;
import com.example.exerice1.entity.Project;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectMapper {
    ProjectDto toDto(Project project);
    Project toEntity(ProjectDto projectDto);
}

