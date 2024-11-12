package com.example.exerice1.mapper.impl;

import com.example.exerice1.dto.ProjectDto;
import com.example.exerice1.dto.TaskDto;
import com.example.exerice1.dto.UserDto;
import com.example.exerice1.entity.Project;
import com.example.exerice1.entity.Task;
import com.example.exerice1.entity.User;
import com.example.exerice1.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }

        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setStatus(task.getStatus());

        if (task.getProject() != null) {
            taskDto.setProject(new ProjectDto(
                    task.getProject().getId(),
                    task.getProject().getName(),
                    task.getProject().getDescription(),
                    null,
                    null
            ));
        }

        // Map nested user to UserDto
        if (task.getUser() != null) {
            taskDto.setUser(new UserDto(
                    task.getUser().getId(),
                    task.getUser().getName(),
                    task.getUser().getEmail(),
                    null
            ));
        }

        return taskDto;
    }

    @Override
    public Task toEntity(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }

        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setStatus(taskDto.getStatus());

        if (taskDto.getProject() != null) {
            task.setProject(new Project(
                    taskDto.getProject().getId(),
                    taskDto.getProject().getName(),
                    taskDto.getProject().getDescription(),
                    null,
                    null
            ));
        }

        if (taskDto.getUser() != null) {
            task.setUser(new User(
                    taskDto.getUser().getId(),
                    taskDto.getUser().getName(),
                    taskDto.getUser().getEmail(),
                    null
            ));
        }

        return task;
    }
}
