package com.example.exerice1.mapper.impl;

import com.example.exerice1.dto.ProjectDto;
import com.example.exerice1.dto.TaskDto;
import com.example.exerice1.dto.UserDto;
import com.example.exerice1.entity.Project;
import com.example.exerice1.entity.Task;
import com.example.exerice1.entity.User;
import com.example.exerice1.mapper.ProjectMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectDto toDto(Project project) {
        if (project == null) {
            return null;
        }

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());

        if (project.getUsers() != null) {
            projectDto.setUsers(
                    project.getUsers().stream()
                            .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), null))
                            .collect(Collectors.toList())
            );
        }

        if (project.getTasks() != null) {
            projectDto.setTasks(
                    project.getTasks().stream()
                            .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getStatus(), null, null))
                            .collect(Collectors.toList())
            );
        }

        return projectDto;
    }

    @Override
    public Project toEntity(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }

        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());

        if (projectDto.getUsers() != null) {
            project.setUsers(
                    projectDto.getUsers().stream()
                            .map(userDto -> new User(userDto.getId(), userDto.getName(), userDto.getEmail(), null))
                            .collect(Collectors.toList())
            );
        }

        if (projectDto.getTasks() != null) {
            project.setTasks(
                    projectDto.getTasks().stream()
                            .map(taskDto -> new Task(taskDto.getId(), taskDto.getTitle(), taskDto.getStatus(), null, null))
                            .collect(Collectors.toList())
            );
        }

        return project;
    }
}
