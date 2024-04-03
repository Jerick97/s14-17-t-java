package com.domain.projects;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.ProjectsModel;
import com.domain.projects.ProjectsRepository;
import com.domain.projects.ProjectsService;

@Service
public class ProjectsServiceImpl implements ProjectsService{

    private final ModelMapper modelMapper;
    private final ProjectsRepository projectsRepository;

    public ProjectsServiceImpl(
        ModelMapper modelMapper,
        ProjectsRepository projectsRepository
    ) {
        this.modelMapper = modelMapper;
        this.projectsRepository = projectsRepository;
    }

    @Transactional
    @Override
    public ProjectsModel createprojects(ProjectsModel projectsModel) {
        
        Projects projects = modelMapper.map(projectsModel, Projects.class);
        projects = projectsRepository.save(projects);
        projectsModel.setId(projects.getId());
        return modelMapper.map(projects, ProjectsModel.class);
    }

    @Transactional
    @Override
    public ProjectsModel updateprojects(Long id, ProjectsModel projectsModel) {

        if (projectsRepository.existsById(id)) {
            Projects projectsActualizado = modelMapper.map(projectsModel, Projects.class);
            projectsRepository.save(projectsActualizado);
            return projectsModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "projects no encontrado para actualizar."
        );
    }

    @Override
    public ProjectsModel getprojectsById(Long id) {

        Projects projects = projectsRepository.findById(id).orElse(null);
        if (projects == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "projects no encontrado."
            );
        }
        return modelMapper.map(projects, ProjectsModel.class);
    }

    @Override
    public List<ProjectsModel> getAllprojects() {

        List<Projects> entity = projectsRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, ProjectsModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteprojects(Long id) {
        if (projectsRepository.existsById(id)) {
            projectsRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "projects no encontrada para eliminar."
            );
        }
    }
}
