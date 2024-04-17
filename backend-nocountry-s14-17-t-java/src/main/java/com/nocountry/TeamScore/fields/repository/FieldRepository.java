package com.nocountry.TeamScore.fields.repository;

import com.nocountry.TeamScore.fields.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
}
