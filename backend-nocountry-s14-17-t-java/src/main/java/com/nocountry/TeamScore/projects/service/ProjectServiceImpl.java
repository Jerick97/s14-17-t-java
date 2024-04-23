package com.nocountry.TeamScore.projects.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.dto.GroupDTO;
import com.nocountry.TeamScore.groups.model.dto.GroupRequest;
import com.nocountry.TeamScore.groups.repository.GroupRepository;
import com.nocountry.TeamScore.groups.service.GroupService;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.model.dto.ProjectDTO;
import com.nocountry.TeamScore.projects.model.dto.ProjectRequest;
import com.nocountry.TeamScore.projects.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ObjectMapper mapper;
    private final GroupRepository groupRepository;

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> projectsDTO = mapper.convertValue(
                projectRepository.findAll(),
                mapper.getTypeFactory().constructCollectionType(List.class, ProjectDTO.class)
        );

        return projectsDTO;
    }
    @Override
    public List<ProjectDTO> getProjectsRecent() {
        return projectRepository.findAll()
                .stream()
                .filter(project -> project.getEndsOn() != null && LocalDateTime.now().isBefore(project.getEndsOn()))
                .map(project -> mapper.convertValue(project, ProjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        return mapper.convertValue(project, ProjectDTO.class);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }

    @Override
    public ProjectDTO createProject(ProjectRequest request) {
        Project project = mapper.convertValue(request, Project.class);
        return mapper.convertValue(
                projectRepository.save(project),
                ProjectDTO.class
        );
    }

    @Override
    public List<ProjectDTO> getProjectByPublishDate(String publishDate) {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> listFilter = projects.stream()
                .map(project -> mapper.convertValue(project, ProjectDTO.class))
                .filter(project -> project.getPublishDate().toString().equals(publishDate))
                .collect(Collectors.toList());
        return listFilter;
    }

    @Override
    public String getDateLimitRecentProject(Long idProject) {
        Project project = projectRepository.findById(idProject).orElseThrow();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return project.getEndsOn().format(formatter);
    }

    @Override
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

    @Override
    public void deleteProject(Long id) {
        if(projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        }else {
            throw new RuntimeException("Project not found");
        }
    }

    public Project addGroupsToProject(Long projectId, List<Long> groupsIds) {
        Project project = findById(projectId);
        if (project == null) {
            throw new EntityNotFoundException("Project not found with id " + projectId);
        }
        for (Long groupId : groupsIds) {
            Group group = groupRepository.findById(groupId).orElseThrow();
            group.setProjectId(project);
            groupRepository.save(group);
        }
        return project;
    }


}
