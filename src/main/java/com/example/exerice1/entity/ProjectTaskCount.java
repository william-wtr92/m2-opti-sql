package com.example.exerice1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_task_count")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTaskCount {

    @Id
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "task_count")
    private Long taskCount;
}
