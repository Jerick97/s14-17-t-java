package com.nocountry.TeamScore.fieldsByRole.repository;

import com.nocountry.TeamScore.fieldsByRole.model.FieldsByRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldsByRoleRepository extends JpaRepository<FieldsByRole, Long> {
}
