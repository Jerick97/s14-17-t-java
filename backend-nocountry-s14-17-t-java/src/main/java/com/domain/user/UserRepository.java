package com.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
