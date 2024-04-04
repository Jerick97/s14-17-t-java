package com.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.users.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
