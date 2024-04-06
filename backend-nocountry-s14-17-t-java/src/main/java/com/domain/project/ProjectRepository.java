package com.domain.project;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.project.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
