package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.GroupUserModel;
import com.domain.groupUser.GroupUserService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/groupUser")
public class GroupUserController {

    private GroupUserService groupUserService;

    public GroupUserController(GroupUserService groupUserService) {
        this.groupUserService = groupUserService;
    }

    @PostMapping
    public ResponseEntity<GroupUserModel> createGroupUser(@RequestBody GroupUserModel groupUserModel) {
        return ResponseEntity.ok( this.groupUserService.createGroupUser(groupUserModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupUserModel> updateGroupUser(@PathVariable Long id, @RequestBody GroupUserModel groupUserModel) {
        return ResponseEntity.ok( this.groupUserService.updateGroupUser(id, groupUserModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupUserModel> getGroupUserById(@PathVariable Long id) {
        return ResponseEntity.ok( this.groupUserService.getGroupUserById(id) );
    }

    @GetMapping
    public ResponseEntity<List<GroupUserModel>> getAllGroupUser() {
        return ResponseEntity.ok( this.groupUserService.getAllGroupUser() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupUser(@PathVariable Long id) {
        groupUserService.deleteGroupUser(id);
        return ResponseEntity.noContent().build();
    }
}
