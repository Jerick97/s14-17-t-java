package com.nocountry.TeamScore.fieldByProject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldByProjectDTO {
    private Long projectId;
    private Long fieldId;
    private Integer order;
    private Integer role;
}
