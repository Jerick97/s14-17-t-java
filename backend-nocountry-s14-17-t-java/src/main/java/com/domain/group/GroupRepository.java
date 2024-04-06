package com.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.group.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    
}
