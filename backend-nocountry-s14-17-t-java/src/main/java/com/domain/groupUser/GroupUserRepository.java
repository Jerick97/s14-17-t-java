package com.domain.groupUser;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.groupUser.GroupUser;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
    
}
