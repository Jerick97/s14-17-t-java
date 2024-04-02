package com.domain.projects;

import java.util.List;
import com.dto.projectsModel;

public interface projectsService {

    projectsModel createprojects(projectsModel projectsModel);
    projectsModel updateprojects(Long projectsId, projectsModel projectsModel);

    projectsModel getprojectsById(Long id);
    List<projectsModel> getAllprojects();

    void deleteprojects(Long projectsId);
}
