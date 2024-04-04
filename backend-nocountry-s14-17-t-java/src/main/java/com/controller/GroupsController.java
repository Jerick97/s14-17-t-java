package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.GroupsModel;
import com.domain.groups.GroupsService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/groups")
public class GroupsController {

    private GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @PostMapping
    public ResponseEntity<GroupsModel> createGroups(@RequestBody GroupsModel groupsModel) {
        return ResponseEntity.ok( this.groupsService.createGroups(groupsModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupsModel> updateGroups(@PathVariable Long id, @RequestBody GroupsModel groupsModel) {
        return ResponseEntity.ok( this.groupsService.updateGroups(id, groupsModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupsModel> getGroupsById(@PathVariable Long id) {
        return ResponseEntity.ok( this.groupsService.getGroupsById(id) );
    }

    @GetMapping
    public ResponseEntity<List<GroupsModel>> getAllGroups() {
        return ResponseEntity.ok( this.groupsService.getAllGroups() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroups(@PathVariable Long id) {
        groupsService.deleteGroups(id);
        return ResponseEntity.noContent().build();
    }
}
