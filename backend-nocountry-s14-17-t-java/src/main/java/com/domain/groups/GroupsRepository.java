package com.domain.groups;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.groups.Groups;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
    
}
