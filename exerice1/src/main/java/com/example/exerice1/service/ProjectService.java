package com.example.exerice1.service;

import com.example.exerice1.dto.ProjectDto;

public interface ProjectService {
    void createProject(ProjectDto projectDto);
    void updateProject(Long id, ProjectDto projectDto);
    ProjectDto getProjectById(Long id);
    void deleteProject(Long id);
}
