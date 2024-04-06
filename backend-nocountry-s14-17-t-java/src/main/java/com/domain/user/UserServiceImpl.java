package com.domain.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.UserModel;
import com.domain.user.User;
import com.domain.user.UserRepository;
import com.domain.user.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(
        ModelMapper modelMapper,
        UserRepository userRepository
    ) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserModel createUser(UserModel userModel) {
        
        User user = modelMapper.map(userModel, User.class);
        user = userRepository.save(user);
        userModel.setId(user.getId());
        return modelMapper.map(user, UserModel.class);
    }

    @Transactional
    @Override
    public UserModel updateUser(Long id, UserModel userModel) {

        if (userRepository.existsById(id)) {
            User userActualizado = modelMapper.map(userModel, User.class);
            userRepository.save(userActualizado);
            return userModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User no encontrado para actualizar."
        );
    }

    @Override
    public UserModel getUserById(Long id) {

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User no encontrado."
            );
        }
        return modelMapper.map(user, UserModel.class);
    }

    @Override
    public List<UserModel> getAllUser() {

        List<User> entity = userRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, UserModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User no encontrada para eliminar."
            );
        }
    }
}
