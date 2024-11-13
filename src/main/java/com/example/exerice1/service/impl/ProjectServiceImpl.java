package com.example.exerice1.service.impl;

import com.example.exerice1.dto.ProjectDto;
import com.example.exerice1.dto.ProjectTaskCountDto;
import com.example.exerice1.entity.Project;
import com.example.exerice1.entity.Task;
import com.example.exerice1.entity.User;
import com.example.exerice1.mapper.ProjectMapper;
import com.example.exerice1.mapper.ProjectTaskCountMapper;
import com.example.exerice1.repository.ProjectRepository;
import com.example.exerice1.repository.ProjectTaskCountRepository;
import com.example.exerice1.repository.TaskRepository;
import com.example.exerice1.repository.UserRepository;
import com.example.exerice1.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private ProjectTaskCountRepository projectTaskCountRepository;
    private ProjectMapper projectMapper;
    private ProjectTaskCountMapper projectTaskCountMapper;

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

    @Override
    public List<ProjectDto> getProjectByName(ProjectDto projectDto) {
        Example<Project> example = Example.of(projectMapper.toEntity(projectDto));

        return projectRepository.findAll(example).stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> getProjectByDescription(ProjectDto projectDto) {
        List<Project> projects = projectRepository.findByNameContainingOrDescriptionContaining(projectDto.getName(), projectDto.getDescription());

        return projects.stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectTaskCountDto> getAllProjectTaskCounts() {
        return projectTaskCountRepository.findAll().stream()
                .map(projectTaskCountMapper::toDto)
                .collect(Collectors.toList());
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
