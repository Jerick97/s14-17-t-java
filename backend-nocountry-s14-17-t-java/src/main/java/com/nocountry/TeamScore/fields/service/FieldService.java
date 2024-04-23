package com.nocountry.TeamScore.fields.service;

import com.nocountry.TeamScore.fields.model.dto.FieldDTO;
import com.nocountry.TeamScore.fields.model.dto.FieldResponseDTO;

import java.util.List;

public interface FieldService {
    FieldResponseDTO createField(FieldDTO fieldDTO) throws Exception;
    FieldResponseDTO updateField(Long fieldId, FieldDTO fieldDTO) throws Exception;

    FieldResponseDTO getFieldById(Long id) throws Exception;
    List<FieldResponseDTO> getAllfields() throws Exception;

    void deleteField(Long fieldId) throws Exception;
}
