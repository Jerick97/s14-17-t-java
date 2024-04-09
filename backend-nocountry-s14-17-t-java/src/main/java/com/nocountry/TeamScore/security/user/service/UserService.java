package com.nocountry.TeamScore.security.user.service;

import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.model.dto.UserUpdateRequest;

public interface UserService {
    void update(UserUpdateRequest userRequest, Long id) ;
    User findByUsername(String username);
    User getByID(Long id);
    void delete(Long id);
}
