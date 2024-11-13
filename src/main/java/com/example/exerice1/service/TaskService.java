package com.example.exerice1.service;

import com.example.exerice1.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    void createTask(TaskDto taskDto);
    void updateTask(Long id, TaskDto taskDto);
    void deleteTask(Long id);
    TaskDto getTask(Long id);
    Page<TaskDto> findByTitle(String title, Pageable pageable);
}
