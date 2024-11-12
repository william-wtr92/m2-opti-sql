package com.example.exerice1.service.impl;

import com.example.exerice1.dto.ProjectDto;
import com.example.exerice1.entity.Project;
import com.example.exerice1.entity.Task;
import com.example.exerice1.entity.User;
import com.example.exerice1.mapper.ProjectMapper;
import com.example.exerice1.repository.ProjectRepository;
import com.example.exerice1.repository.TaskRepository;
import com.example.exerice1.repository.UserRepository;
import com.example.exerice1.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void createProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);

        if (projectDto.getUsers() != null) {
            updateUsers(project, projectDto);
        }

        if(projectDto.getTasks() != null) {
            updateTasks(project, projectDto);
        }

        projectRepository.save(project);
    }

    @Override
    public void  updateProject(Long id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return;
        }

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());

        if (projectDto.getUsers() != null) {
            updateUsers(project, projectDto);
        }

        if(projectDto.getTasks() != null) {
            updateTasks(project, projectDto);
        }

        projectRepository.save(project);
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);

        return projectMapper.toDto(project);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        project.getUsers().forEach(user -> user.getProjects().remove(project));
        project.setUsers(null);

        projectRepository.delete(project);
    }


    private void updateUsers(Project project, ProjectDto projectDto) {
        List<User> users = projectDto.getUsers().stream()
                .map(userDto -> userRepository.findById(userDto.getId())
                        .orElseThrow(() -> new IllegalArgumentException("User with ID " + userDto.getId() + " not found")))
                .collect(Collectors.toList());
        project.setUsers(users);

        users.forEach(user -> {
            if (user.getProjects() == null) {
                user.setProjects(List.of(project));
            } else if (!user.getProjects().contains(project)) {
                user.getProjects().add(project);
            }
        });
    }

    private void updateTasks(Project project, ProjectDto projectDto) {
        List<Task> tasks = projectDto.getTasks().stream()
                .map(taskDto -> taskRepository.findById(taskDto.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Task with ID " + taskDto.getId() + " not found")))
                .collect(Collectors.toList());
        project.setTasks(tasks);

        tasks.forEach(task -> {
            if (task.getProject() == null) {
                task.setProject(project);
            } else if (!task.getProject().equals(project)) {
                task.setProject(project);
            }
        });
    }
}
