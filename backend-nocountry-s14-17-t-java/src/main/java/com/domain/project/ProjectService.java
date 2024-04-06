package com.domain.project;

import java.util.List;
import com.dto.ProjectModel;

public interface ProjectService {

    ProjectModel createProject(ProjectModel projectModel);
    ProjectModel updateProject(Long projectId, ProjectModel projectModel);

    ProjectModel getProjectById(Long id);
    ProjectModel getProjectByDatePublish();

    List<ProjectModel> getAllProject();

    void deleteProject(Long projectId);
}
