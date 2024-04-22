package com.nocountry.TeamScore.fieldByProject.repository;

import com.nocountry.TeamScore.fieldByProject.model.FieldByProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldByProjectRepository extends JpaRepository<FieldByProject, Long> {
}
