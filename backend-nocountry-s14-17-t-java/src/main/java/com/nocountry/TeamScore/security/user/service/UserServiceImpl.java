package com.nocountry.TeamScore.security.user.service;

import com.nocountry.TeamScore.fieldByProject.repository.FieldByProjectRepository;
import com.nocountry.TeamScore.fields.service.FieldService;
import com.nocountry.TeamScore.feedback.repository.FeedbackRepository;
import com.nocountry.TeamScore.groups.repository.GroupByUserRepository;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.model.dto.UserUpdateRequest;

import com.nocountry.TeamScore.security.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final GroupByUserRepository groupByUserRepository;
    private final FeedbackRepository feedbackRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final FieldService fieldService;
    private final FieldByProjectRepository fieldByProjectRepository;
  
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

    @Override
    public User getOrCreateUser(String username, String name, String surname) {
        return userRepository.findByEmail(username)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(username);
                    newUser.setName(name);
                    newUser.setSurname(surname);
                    return userRepository.save(newUser);
                });
    }

    public List<User> getUsersSinVotar(Long userId, Long groupId) {
        List<User> usersByGroup = groupByUserRepository.findByGroupId(groupId)
                .stream()
                .filter(u -> !u.getUser().getId().equals(userId))
                .map(u -> u.getUser())
                .toList();


        User evaluador = userRepository.findById(userId).orElseThrow( () -> new EntityNotFoundException("User" + userId + "not found"));

        return usersByGroup.stream()
                .filter(user -> !feedbackRepository.existsByUsuarioQueEvaluaAndUsuarioEvaluado(evaluador, user))
                .collect(Collectors.toList());
    }
}