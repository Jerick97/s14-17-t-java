package com.domain.projects;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.projectsModel;
import com.domain.projects.projects;
import com.domain.projects.projectsRepository;
import com.domain.projects.projectsService;

@Service
public class projectsServiceImpl implements projectsService{

    private final ModelMapper modelMapper;
    private final projectsRepository projectsRepository;

    public projectsServiceImpl(
        ModelMapper modelMapper,
        projectsRepository projectsRepository
    ) {
        this.modelMapper = modelMapper;
        this.projectsRepository = projectsRepository;
    }

    @Transactional
    @Override
    public projectsModel createprojects(projectsModel projectsModel) {
        
        projects projects = modelMapper.map(projectsModel, projects.class);
        projects = projectsRepository.save(projects);
        projectsModel.setId(projects.getId());
        return modelMapper.map(projects, projectsModel.class);
    }

    @Transactional
    @Override
    public projectsModel updateprojects(Long id, projectsModel projectsModel) {

        if (projectsRepository.existsById(id)) {
            projects projectsActualizado = modelMapper.map(projectsModel, projects.class);
            projectsRepository.save(projectsActualizado);
            return projectsModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "projects no encontrado para actualizar."
        );
    }

    @Override
    public projectsModel getprojectsById(Long id) {

        projects projects = projectsRepository.findById(id).orElse(null);
        if (projects == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "projects no encontrado."
            );
        }
        return modelMapper.map(projects, projectsModel.class);
    }

    @Override
    public List<projectsModel> getAllprojects() {

        List<projects> entity = projectsRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, projectsModel.class))
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
