package com.example.exerice1.service.impl;

import com.example.exerice1.dto.TaskDto;
import com.example.exerice1.entity.Task;
import com.example.exerice1.mapper.TaskMapper;
import com.example.exerice1.repository.ProjectRepository;
import com.example.exerice1.repository.TaskRepository;
import com.example.exerice1.repository.UserRepository;
import com.example.exerice1.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private TaskMapper taskMapper;

    @Override
    public void createTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);

        if (taskDto.getProject() != null) {
            task.setProject(projectRepository.findById(taskDto.getProject().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Project with ID " + taskDto.getProject().getId() + " not found")));
        }

        if (taskDto.getUser() != null) {
            task.setUser(userRepository.findById(taskDto.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("User with ID " + taskDto.getUser().getId() + " not found")));
        }

        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));

        task.setTitle(taskDto.getTitle());
        task.setStatus(taskDto.getStatus());

        if (taskDto.getProject() != null) {
            task.setProject(projectRepository.findById(taskDto.getProject().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Project with ID " + taskDto.getProject().getId() + " not found")));
        }

        if (taskDto.getUser() != null) {
            task.setUser(userRepository.findById(taskDto.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("User with ID " + taskDto.getUser().getId() + " not found")));
        }

        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));

        taskRepository.delete(task);
    }

    @Override
    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));

        return taskMapper.toDto(task);
    }

}
