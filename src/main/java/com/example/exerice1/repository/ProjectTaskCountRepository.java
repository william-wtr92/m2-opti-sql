package com.example.exerice1.repository;

import com.example.exerice1.entity.ProjectTaskCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectTaskCountRepository extends JpaRepository<ProjectTaskCount, Long> {
    @Modifying
    @Transactional
    @Query(value = "REFRESH MATERIALIZED VIEW project_task_count", nativeQuery = true)
    void refreshMaterializedView();
}
