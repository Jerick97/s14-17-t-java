package com.domain.fields;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.fields.Fields;

public interface FieldsRepository extends JpaRepository<Fields, Long> {
    
}
