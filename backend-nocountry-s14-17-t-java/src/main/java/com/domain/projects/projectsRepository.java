package com.domain.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.projects.projects;

public interface projectsRepository extends JpaRepository<projects, Long> {
    
}
