package com.nocountry.TeamScore.fields.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.TeamScore.fields.model.Field;
import com.nocountry.TeamScore.fields.model.dto.FieldDTO;
import com.nocountry.TeamScore.fields.repository.FieldRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
public class FieldServiceImpl implements FieldService{

    private final FieldRepository fieldRepository;
    private final ObjectMapper mapper;

    public FieldServiceImpl(FieldRepository fieldRepository, ObjectMapper mapper){
        this.fieldRepository = fieldRepository;
        this.mapper = mapper;
    }

    @Override
    public FieldDTO createField(FieldDTO fieldDTO) throws Exception {
        try {
            Field field = mapper.convertValue(fieldDTO, Field.class);
            Field savedField = fieldRepository.save(field);
            return mapper.convertValue(savedField, FieldDTO.class);
        } catch (Exception e) {
            throw new Exception("Error creating field", e);
        }
    }

    @Override
    public FieldDTO updateField(Long fieldId, FieldDTO fieldDTO) throws Exception {
        try {
            if (fieldRepository.existsById(fieldId)) {
                Field updatedField = mapper.convertValue(fieldDTO, Field.class);
                updatedField.setId(fieldId);
                Field savedField = fieldRepository.save(updatedField);
                return mapper.convertValue(savedField, FieldDTO.class);
            } else {
                throw new EntityNotFoundException("Field with ID " + fieldId + " not found");
            }
        } catch (Exception e) {
            throw new Exception("Error updating field: " + e.getMessage());
        }
    }

    @Override
    public FieldDTO getFieldById(Long id) throws Exception {
        try {
            Field field = fieldRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Field not found."));
            return mapper.convertValue(field, FieldDTO.class);
        } catch (Exception e) {
            throw new Exception("Error retrieving field: " + e.getMessage());
        }
    }

    @Override
    public List<FieldDTO> getAllfields() throws Exception {
        try {
            List<Field> fields = fieldRepository.findAll();
            return fields.stream()
                    .map(field -> mapper.convertValue(field, FieldDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error retrieving fields: " + e.getMessage());
        }
    }

    @Override
    public void deleteField(Long fieldId) throws Exception {
        try {
            if (!fieldRepository.existsById(fieldId)) {
                throw new EntityNotFoundException();
            }
            fieldRepository.deleteById(fieldId);
        } catch (Exception e) {
            throw new Exception("Error deleting field: " + e.getMessage());
        }
    }

}
