package com.nocountry.TeamScore.projects.controller;

import com.nocountry.TeamScore.groups.model.dto.GroupRequest;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.model.dto.ProjectDTO;
import com.nocountry.TeamScore.projects.model.dto.ProjectRequest;
import com.nocountry.TeamScore.projects.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/projects")
@Tag(name = "Projects", description = "Endpoints for projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @Operation(summary = "Get all projects", description = "This endpoint return all projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/recent")
    public ResponseEntity<List<ProjectDTO>> getProjectsRecent() {
        return ResponseEntity.ok(projectService.getProjectsRecent());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by id", description = "This endpoint return a project by id")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/by-date/{date}")
    @Operation(summary = "Get project by publish date", description = "Get a list of projects by a specific date")
    public ResponseEntity<List<ProjectDTO>> getProjectByPublishDate(@PathVariable String date) {
        return ResponseEntity.ok(projectService.getProjectByPublishDate(date));
    }

    @GetMapping("/{id}/limit-date")
    public ResponseEntity<String> getDateLimitRecentProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getDateLimitRecentProject(id));
    }

    @PostMapping
    @Operation(summary = "Create project", description = "This endpoint create a new project")
    public ResponseEntity<ProjectDTO> save(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.createProject(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update project", description = "This endpoint update a project by id")
    public ResponseEntity<ProjectDTO> update(@PathVariable Long id, @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.updateProject(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete project", description = "This endpoint delete a project by id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{projectId}/groups")
    @Operation(summary = "Add groups to project", description = "This endpoint adds one or more groups to a project, necesita que los grupos existan, y proporcionar el arrays solo con sus ids")
    public ResponseEntity<Project> addGroupsToProject(@PathVariable Long projectId, @RequestBody List<Long> groupsIds) {
        return ResponseEntity.ok(projectService.addGroupsToProject(projectId, groupsIds));
    }
}
