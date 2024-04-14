package com.nocountry.TeamScore.fieldByProject.model.dto;

import com.nocountry.TeamScore.fields.model.dto.FieldDTO;
import com.nocountry.TeamScore.projects.model.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldByProyectResponseDTO {
    private Long id;
    private Long projectId;
    private Long fieldId;
    private Integer order;
    private Integer role;
}
