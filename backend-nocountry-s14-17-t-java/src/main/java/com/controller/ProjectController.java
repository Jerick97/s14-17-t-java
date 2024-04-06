package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.ProjectModel;
import com.domain.project.ProjectService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectModel> createProject(@RequestBody ProjectModel projectModel) {
        return ResponseEntity.ok( this.projectService.createProject(projectModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectModel> updateProject(@PathVariable Long id, @RequestBody ProjectModel projectModel) {
        return ResponseEntity.ok( this.projectService.updateProject(id, projectModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectModel> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok( this.projectService.getProjectById(id) );
    }

    @GetMapping
    public ResponseEntity<List<ProjectModel>> getAllProject() {
        return ResponseEntity.ok( this.projectService.getAllProject() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
