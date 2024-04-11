package com.nocountry.TeamScore.fields.service;

import com.nocountry.TeamScore.fields.model.dto.FieldDTO;

import java.util.List;

public interface FieldService {
    FieldDTO createField(FieldDTO fieldDTO) throws Exception;
    FieldDTO updateField(Long fieldId, FieldDTO fieldDTO) throws Exception;

    FieldDTO getFieldById(Long id) throws Exception;
    List<FieldDTO> getAllfields() throws Exception;

    void deleteField(Long fieldId) throws Exception;
}
