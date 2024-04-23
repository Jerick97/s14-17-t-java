package com.nocountry.TeamScore.fieldByProject.service;

import com.nocountry.TeamScore.fieldByProject.model.FieldByProject;
import com.nocountry.TeamScore.fieldByProject.model.dto.FieldByProjectDTO;
import com.nocountry.TeamScore.fieldByProject.model.dto.FieldByProyectResponseDTO;

import java.util.List;

public interface FieldByProjectService {

    FieldByProyectResponseDTO createFieldByProject(FieldByProjectDTO fieldByProjectDTO) throws Exception;

    FieldByProyectResponseDTO updateFieldByProject(Long id, FieldByProjectDTO fieldByProjectDTO) throws  Exception;

    FieldByProyectResponseDTO getFieldByProjectById(Long id) throws Exception;

    List<FieldByProyectResponseDTO> getAllFieldByProject() throws Exception;

    void deleteFieldByProject(Long id) throws Exception;

}
