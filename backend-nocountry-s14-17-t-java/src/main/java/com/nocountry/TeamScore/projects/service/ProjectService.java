package com.nocountry.TeamScore.projects.service;

import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.model.dto.ProjectDTO;
import com.nocountry.TeamScore.projects.model.dto.ProjectRequest;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();
    ProjectDTO getProjectById(Long id);

    Project findById(Long id);
    ProjectDTO createProject(ProjectRequest request);

    ProjectDTO updateProject(Long id, ProjectRequest request);
    void deleteProject(Long id);
}
