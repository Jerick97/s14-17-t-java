package com.domain.users;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.UsersModel;
import com.domain.users.Users;
import com.domain.users.UsersRepository;
import com.domain.users.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

    private final ModelMapper modelMapper;
    private final UsersRepository usersRepository;

    public UsersServiceImpl(
        ModelMapper modelMapper,
        UsersRepository usersRepository
    ) {
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
    }

    @Transactional
    @Override
    public UsersModel createUsers(UsersModel usersModel) {
        
        Users users = modelMapper.map(usersModel, Users.class);
        users = usersRepository.save(users);
        usersModel.setId(users.getId());
        return modelMapper.map(users, UsersModel.class);
    }

    @Transactional
    @Override
    public UsersModel updateUsers(Long id, UsersModel usersModel) {

        if (usersRepository.existsById(id)) {
            Users usersActualizado = modelMapper.map(usersModel, Users.class);
            usersRepository.save(usersActualizado);
            return usersModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Users no encontrado para actualizar."
        );
    }

    @Override
    public UsersModel getUsersById(Long id) {

        Users users = usersRepository.findById(id).orElse(null);
        if (users == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Users no encontrado."
            );
        }
        return modelMapper.map(users, UsersModel.class);
    }

    @Override
    public List<UsersModel> getAllUsers() {

        List<Users> entity = usersRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, UsersModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteUsers(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Users no encontrada para eliminar."
            );
        }
    }
}
