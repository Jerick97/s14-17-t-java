package com.domain.project;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.ProjectModel;
import com.domain.project.Project;
import com.domain.project.ProjectRepository;
import com.domain.project.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(
        ModelMapper modelMapper,
        ProjectRepository projectRepository
    ) {
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
    }

    @Transactional
    @Override
    public ProjectModel createProject(ProjectModel projectModel) {
        
        Project project = modelMapper.map(projectModel, Project.class);
        project = projectRepository.save(project);
        projectModel.setId(project.getId());
        return modelMapper.map(project, ProjectModel.class);
    }

    @Transactional
    @Override
    public ProjectModel updateProject(Long id, ProjectModel projectModel) {

        if (projectRepository.existsById(id)) {
            Project projectActualizado = modelMapper.map(projectModel, Project.class);
            projectRepository.save(projectActualizado);
            return projectModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Project no encontrado para actualizar."
        );
    }

    @Override
    public ProjectModel getProjectById(Long id) {

        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Project no encontrado."
            );
        }
        return modelMapper.map(project, ProjectModel.class);
    }

    @Override
    public List<ProjectModel> getAllProject() {

        List<Project> entity = projectRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, ProjectModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Project no encontrada para eliminar."
            );
        }
    }
}
