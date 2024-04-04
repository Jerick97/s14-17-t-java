package com.domain.users;

import java.util.List;
import com.dto.UsersModel;

public interface UsersService {

    UsersModel createUsers(UsersModel usersModel);
    UsersModel updateUsers(Long usersId, UsersModel usersModel);

    UsersModel getUsersById(Long id);
    List<UsersModel> getAllUsers();

    void deleteUsers(Long usersId);
}
