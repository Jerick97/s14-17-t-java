package com.nocountry.TeamScore.projects.service;

import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.model.dto.ProjectDTO;
import com.nocountry.TeamScore.projects.model.dto.ProjectRequest;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();
    List<ProjectDTO> getProjectsRecent();
    ProjectDTO getProjectById(Long id);
    String getDateLimitRecentProject(Long idProject);

    Project findById(Long id);

    List<ProjectDTO> getProjectByPublishDate(String publishDate);
    ProjectDTO createProject(ProjectRequest request);

    ProjectDTO updateProject(Long id, ProjectRequest request);
    void deleteProject(Long id);
}
