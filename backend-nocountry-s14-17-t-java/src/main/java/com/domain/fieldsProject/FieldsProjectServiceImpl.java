package com.domain.fieldsProject;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.FieldsProjectModel;

@Service
public class FieldsProjectServiceImpl implements FieldsProjectService{

    private final ModelMapper modelMapper;
    private final FieldsProjectRepository fieldsProjectRepository;

    public FieldsProjectServiceImpl(
        ModelMapper modelMapper,
        FieldsProjectRepository fieldsProjectRepository
    ) {
        this.modelMapper = modelMapper;
        this.fieldsProjectRepository = fieldsProjectRepository;
    }

    @Transactional
    @Override
    public FieldsProjectModel createFieldsProject(FieldsProjectModel fieldsProjectModel) {
        
        FieldsProject fieldsProject = modelMapper.map(fieldsProjectModel, FieldsProject.class);
        fieldsProject = fieldsProjectRepository.save(fieldsProject);
        fieldsProjectModel.setId(fieldsProject.getId());
        return modelMapper.map(fieldsProject, FieldsProjectModel.class);
    }

    @Transactional
    @Override
    public FieldsProjectModel updateFieldsProject(Long id, FieldsProjectModel fieldsProjectModel) {

        if (fieldsProjectRepository.existsById(id)) {
            FieldsProject fieldsProjectActualizado = modelMapper.map(fieldsProjectModel, FieldsProject.class);
            fieldsProjectRepository.save(fieldsProjectActualizado);
            return fieldsProjectModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "FieldsProject no encontrado para actualizar."
        );
    }

    @Override
    public FieldsProjectModel getFieldsProjectById(Long id) {

        FieldsProject fieldsProject = fieldsProjectRepository.findById(id).orElse(null);
        if (fieldsProject == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "FieldsProject no encontrado."
            );
        }
        return modelMapper.map(fieldsProject, FieldsProjectModel.class);
    }

    @Override
    public List<FieldsProjectModel> getAllFieldsProject() {

        List<FieldsProject> entity = fieldsProjectRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, FieldsProjectModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteFieldsProject(Long id) {
        if (fieldsProjectRepository.existsById(id)) {
            fieldsProjectRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "FieldsProject no encontrada para eliminar."
            );
        }
    }
}
