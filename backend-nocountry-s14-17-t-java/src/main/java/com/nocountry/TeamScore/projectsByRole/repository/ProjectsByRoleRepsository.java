package com.nocountry.TeamScore.projectsByRole.repository;

import com.nocountry.TeamScore.projectsByRole.model.ProjectsByRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsByRoleRepsository extends JpaRepository<ProjectsByRole, Long> {
}
