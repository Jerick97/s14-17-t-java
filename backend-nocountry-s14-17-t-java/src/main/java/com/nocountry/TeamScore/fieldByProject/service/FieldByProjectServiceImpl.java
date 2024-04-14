package com.nocountry.TeamScore.fieldByProject.service;

import com.nocountry.TeamScore.fieldByProject.model.FieldByProject;
import com.nocountry.TeamScore.fieldByProject.model.dto.FieldByProjectDTO;
import com.nocountry.TeamScore.fieldByProject.model.dto.FieldByProyectResponseDTO;
import com.nocountry.TeamScore.fieldByProject.repository.FieldByProjectRepository;
import com.nocountry.TeamScore.fields.model.Field;
import com.nocountry.TeamScore.fields.repository.FieldRepository;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
public class FieldByProjectServiceImpl implements FieldByProjectService {

    private final ProjectRepository projectRepository;
    private final FieldRepository fieldRepository;
    private final FieldByProjectRepository fieldByProjectRepository;

    public FieldByProjectServiceImpl(FieldByProjectRepository fieldByProjectRepository, ProjectRepository projectRepository, FieldRepository fieldRepository){
        this.fieldByProjectRepository = fieldByProjectRepository;
        this.projectRepository = projectRepository;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public FieldByProyectResponseDTO createFieldByProject(FieldByProjectDTO fieldByProjectDTO) throws Exception {
        try {
            Project project = projectRepository.findById(fieldByProjectDTO.getProjectId())
                    .orElseThrow(() -> new Exception("Project not found"));
            Field field = fieldRepository.findById(fieldByProjectDTO.getFieldId())
                    .orElseThrow(() -> new Exception("Field not found"));

            FieldByProject savedFieldByProject = FieldByProject.builder()
                    .order(fieldByProjectDTO.getOrder())
                    .role(fieldByProjectDTO.getRole())
                    .project(project)
                    .field(field)
                    .build();
            FieldByProject createdFieldByProject = fieldByProjectRepository.save(savedFieldByProject);

            /*Aqui se construye la respuesta*/
            FieldByProyectResponseDTO responseDTO = new FieldByProyectResponseDTO();
            responseDTO.setId(createdFieldByProject.getId());
            responseDTO.setProjectId(project.getId());
            responseDTO.setFieldId(field.getId());
            responseDTO.setOrder(createdFieldByProject.getOrder());
            responseDTO.setRole(createdFieldByProject.getRole());
            return responseDTO;
        } catch (Exception e) {
            throw new Exception("Error creating Field by Project", e);
        }
    }

    @Override
    public FieldByProyectResponseDTO updateFieldByProject(Long id, FieldByProjectDTO fieldByProjectDTO) throws Exception {
        try {
            FieldByProject existingFieldByProject = fieldByProjectRepository.findById(id)
                    .orElseThrow(() -> new Exception("Field by Project not found"));

            existingFieldByProject.setOrder(fieldByProjectDTO.getOrder());
            existingFieldByProject.setRole(fieldByProjectDTO.getRole());
            FieldByProject savedEntity = fieldByProjectRepository.save(existingFieldByProject);

            /*Aqui se construye la respuesta*/
            FieldByProyectResponseDTO responseDTO = new FieldByProyectResponseDTO();
            responseDTO.setId(savedEntity.getId());
            responseDTO.setProjectId(savedEntity.getProject().getId());
            responseDTO.setFieldId(savedEntity.getField().getId());
            responseDTO.setOrder(savedEntity.getOrder());
            responseDTO.setRole(savedEntity.getRole());

            return responseDTO;
        } catch (Exception e) {
            throw new Exception("Error updating Field by Project", e);
        }
    }

    @Override
    public FieldByProyectResponseDTO getFieldByProjectById(Long id) throws Exception {
        try {
            FieldByProject fieldByProject = fieldByProjectRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Field By Project not found."));

            /*Aqui se construye la respuesta*/
            FieldByProyectResponseDTO responseDTO = new FieldByProyectResponseDTO();
            responseDTO.setId(fieldByProject.getId());
            responseDTO.setProjectId(fieldByProject.getProject().getId());
            responseDTO.setFieldId(fieldByProject.getField().getId());
            responseDTO.setOrder(fieldByProject.getOrder());
            responseDTO.setRole(fieldByProject.getRole());

            return responseDTO;
        } catch (Exception e) {
            throw new Exception("Error retrieving field by project: " + e.getMessage());
        }
    }

    @Override
    public List<FieldByProyectResponseDTO> getAllFieldByProject() throws Exception {
        try {
            List<FieldByProject> fieldByProjects = fieldByProjectRepository.findAll();
            return fieldByProjects.stream()
                    .map(field -> {
                        /*Aqui se construye la respuesta*/
                        FieldByProyectResponseDTO responseDTO = new FieldByProyectResponseDTO();
                        responseDTO.setId(field.getId());
                        responseDTO.setOrder(field.getOrder());
                        responseDTO.setRole(field.getRole());
                        responseDTO.setProjectId(field.getProject().getId());
                        responseDTO.setFieldId(field.getField().getId());

                        return responseDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error retrieving fields by project: " + e.getMessage());
        }
    }

    @Override
    public void deleteFieldByProject(Long id) throws Exception {
        try {
            if(!fieldByProjectRepository.existsById(id)){
                throw new EntityNotFoundException();
            }
            fieldByProjectRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error deleting Field by Project with id: " + id, e);
        }
    }
}
