package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.UserModel;
import com.domain.user.UserService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        return ResponseEntity.ok( this.userService.createUser(userModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel userModel) {
        return ResponseEntity.ok( this.userService.updateUser(id, userModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok( this.userService.getUserById(id) );
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUser() {
        return ResponseEntity.ok( this.userService.getAllUser() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
