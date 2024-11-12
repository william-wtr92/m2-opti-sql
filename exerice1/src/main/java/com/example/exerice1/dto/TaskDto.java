package com.example.exerice1.dto;

import com.example.exerice1.utils.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private TaskStatus status;
    private ProjectDto project;
    private UserDto user;
}
