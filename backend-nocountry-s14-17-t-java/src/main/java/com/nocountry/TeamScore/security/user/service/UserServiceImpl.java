package com.nocountry.TeamScore.security.user.service;

import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.model.dto.UserUpdateRequest;
import com.nocountry.TeamScore.security.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public void update(UserUpdateRequest userRequest, Long id) {

        User user = userRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("User " + id + " not found"));
        log.info(user.getAuthorities().toString());

        if (userRequest.getName() != null)
            user.setName(userRequest.getName());
        if (userRequest.getSurname() != null)
            user.setSurname(userRequest.getSurname());
        if (userRequest.getStatus() != null)
            user.setStatus(userRequest.getStatus());
        if (userRequest.getOperador() != null)
            user.setOperador(userRequest.getOperador());

        // cambios de email o password, testear bien:

        if (userRequest.getEmail() != null)
            user.setEmail(userRequest.getEmail());
        if (userRequest.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        userRepository.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow( () -> new EntityNotFoundException("User " + username + "not found"));
    }

    @Override
    public User getByID(Long id) {
        return userRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("User" + id + "not found"));
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("User" + id + "not found"));

        user.setIsEnabled(false);
        userRepository.save(user);
    }

    @Override
    public long countByStatus(String status) {
        return userRepository.countByStatus(status);
    }
}