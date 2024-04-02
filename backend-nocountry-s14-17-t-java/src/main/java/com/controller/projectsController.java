package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.projectsModel;
import com.domain.projects.projectsService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/projects")
public class projectsController {

    private projectsService projectsService;

    public projectsController(projectsService projectsService) {
        this.projectsService = projectsService;
    }

    @PostMapping
    public ResponseEntity<projectsModel> createprojects(@RequestBody projectsModel projectsModel) {
        return ResponseEntity.ok( this.projectsService.createprojects(projectsModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<projectsModel> updateprojects(@PathVariable Long id, @RequestBody projectsModel projectsModel) {
        return ResponseEntity.ok( this.projectsService.updateprojects(id, projectsModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<projectsModel> getprojectsById(@PathVariable Long id) {
        return ResponseEntity.ok( this.projectsService.getprojectsById(id) );
    }

    @GetMapping
    public ResponseEntity<List<projectsModel>> getAllprojects() {
        return ResponseEntity.ok( this.projectsService.getAllprojects() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteprojects(@PathVariable Long id) {
        projectsService.deleteprojects(id);
        return ResponseEntity.noContent().build();
    }
}
