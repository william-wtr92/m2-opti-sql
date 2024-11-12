package com.example.exerice1.repository;

import com.example.exerice1.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByNameContainingOrDescriptionContaining(String name, String description);
}
