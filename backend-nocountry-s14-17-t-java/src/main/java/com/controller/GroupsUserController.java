package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.GroupsUserModel;
import com.domain.groupsUser.GroupsUserService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/groupsUser")
public class GroupsUserController {

    private GroupsUserService groupsUserService;

    public GroupsUserController(GroupsUserService groupsUserService) {
        this.groupsUserService = groupsUserService;
    }

    @PostMapping
    public ResponseEntity<GroupsUserModel> createGroupsUser(@RequestBody GroupsUserModel groupsUserModel) {
        return ResponseEntity.ok( this.groupsUserService.createGroupsUser(groupsUserModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupsUserModel> updateGroupsUser(@PathVariable Long id, @RequestBody GroupsUserModel groupsUserModel) {
        return ResponseEntity.ok( this.groupsUserService.updateGroupsUser(id, groupsUserModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupsUserModel> getGroupsUserById(@PathVariable Long id) {
        return ResponseEntity.ok( this.groupsUserService.getGroupsUserById(id) );
    }

    @GetMapping
    public ResponseEntity<List<GroupsUserModel>> getAllGroupsUser() {
        return ResponseEntity.ok( this.groupsUserService.getAllGroupsUser() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupsUser(@PathVariable Long id) {
        groupsUserService.deleteGroupsUser(id);
        return ResponseEntity.noContent().build();
    }
}
