package com.example.exerice1.controller;

import com.example.exerice1.dto.ProjectDto;
import com.example.exerice1.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<String> createProject(@Valid @RequestBody ProjectDto projectDto) {
        projectService.createProject(projectDto);

        return ResponseEntity.ok("Project created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDto projectDto) {
        projectService.updateProject(id, projectDto);

        return ResponseEntity.ok("Project updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        ProjectDto projectDto = projectService.getProjectById(id);

        return ResponseEntity.ok(projectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);

        return ResponseEntity.ok("Project deleted");
    }
}
