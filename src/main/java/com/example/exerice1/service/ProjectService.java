package com.example.exerice1.service;

import com.example.exerice1.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    void createProject(ProjectDto projectDto);
    void updateProject(Long id, ProjectDto projectDto);
    ProjectDto getProjectById(Long id);
    void deleteProject(Long id);
    List<ProjectDto> getProjectByName(ProjectDto projectDto);
    List<ProjectDto> getProjectByDescription(ProjectDto projectDto);
}
