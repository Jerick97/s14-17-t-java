package com.nocountry.TeamScore.projects.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.model.dto.ProjectDTO;
import com.nocountry.TeamScore.projects.model.dto.ProjectRequest;
import com.nocountry.TeamScore.projects.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ObjectMapper mapper;

    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> projectsDTO = mapper.convertValue(
                projectRepository.findAll(),
                mapper.getTypeFactory().constructCollectionType(List.class, ProjectDTO.class)
        );

        return projectsDTO;
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        return mapper.convertValue(project, ProjectDTO.class);
    }

    public ProjectDTO createProject(ProjectRequest request) {
        Project project = mapper.convertValue(request, Project.class);
        return mapper.convertValue(
                projectRepository.save(project),
                ProjectDTO.class
        );
    }

    public ProjectDTO updateProject(Long id, ProjectRequest request) {
        Project projectDB = projectRepository.findById(id).orElseThrow();

        if(projectDB != null) {
                  projectDB.setName(request.getName());
                   projectDB.setDescription(request.getDescription());
                   projectDB.setStartsOn(request.getStartsOn());
                   projectDB.setEndsOn(request.getEndsOn());
                   projectRepository.save(projectDB);
                   return mapper.convertValue(projectDB, ProjectDTO.class);
        }else {
            throw new RuntimeException("Project not found");
        }

    }

    public void deleteProject(Long id) {
        if(projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        }else {
            throw new RuntimeException("Project not found");
        }
    }



}
