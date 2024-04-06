package com.domain.user;

import java.util.List;
import com.dto.UserModel;

public interface UserService {

    UserModel createUser(UserModel userModel);
    UserModel getUserByUserNameCreate(UserModel userModel);

    UserModel updateUser(Long userId, UserModel userModel);

    UserModel getUserById(Long id);
    
    List<UserModel> getAllUser();
    
    void deleteUser(Long userId);
    
    void sentMessageEmail(UserModel userModel);
}
