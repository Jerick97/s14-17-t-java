package com.nocountry.TeamScore.security.user.service;

import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.model.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    void update(UserUpdateRequest userRequest, Long id) ;
    User findByUsername(String username);
    User getByID(Long id);
    void delete(Long id);

    // Nuevo método para contar usuarios por estado
    long countByStatus(String status);

    User getOrCreateUser(String username, String name, String surname);

    List<User> getUsersSinVotar(Long userId, Long groupId);
}
