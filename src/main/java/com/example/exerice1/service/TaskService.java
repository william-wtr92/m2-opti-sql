package com.example.exerice1.service;

import com.example.exerice1.dto.TaskDto;

public interface TaskService {
    void createTask(TaskDto taskDto);
    void updateTask(Long id, TaskDto taskDto);
    void deleteTask(Long id);
    TaskDto getTask(Long id);
}
