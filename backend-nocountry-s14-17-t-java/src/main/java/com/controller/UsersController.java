package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.UsersModel;
import com.domain.users.UsersService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UsersModel> createUsers(@RequestBody UsersModel usersModel) {
        return ResponseEntity.ok( this.usersService.createUsers(usersModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersModel> updateUsers(@PathVariable Long id, @RequestBody UsersModel usersModel) {
        return ResponseEntity.ok( this.usersService.updateUsers(id, usersModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersModel> getUsersById(@PathVariable Long id) {
        return ResponseEntity.ok( this.usersService.getUsersById(id) );
    }

    @GetMapping
    public ResponseEntity<List<UsersModel>> getAllUsers() {
        return ResponseEntity.ok( this.usersService.getAllUsers() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        usersService.deleteUsers(id);
        return ResponseEntity.noContent().build();
    }
}
