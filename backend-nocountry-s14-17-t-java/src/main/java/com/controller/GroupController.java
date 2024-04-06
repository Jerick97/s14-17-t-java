package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.GroupModel;
import com.domain.group.GroupService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/group")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<GroupModel> createGroup(@RequestBody GroupModel groupModel) {
        return ResponseEntity.ok( this.groupService.createGroup(groupModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupModel> updateGroup(@PathVariable Long id, @RequestBody GroupModel groupModel) {
        return ResponseEntity.ok( this.groupService.updateGroup(id, groupModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupModel> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok( this.groupService.getGroupById(id) );
    }

    @GetMapping
    public ResponseEntity<List<GroupModel>> getAllGroup() {
        return ResponseEntity.ok( this.groupService.getAllGroup() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
