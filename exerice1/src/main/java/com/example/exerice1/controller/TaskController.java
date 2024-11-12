package com.example.exerice1.controller;

import com.example.exerice1.dto.TaskDto;
import com.example.exerice1.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<String> createTask(@Valid @RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);

        return ResponseEntity.ok("Task created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        TaskDto taskDto = taskService.getTask(id);

        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) {
        taskService.updateTask(id, taskDto);

        return ResponseEntity.ok("Task updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        return ResponseEntity.ok("Task deleted");
    }
}
