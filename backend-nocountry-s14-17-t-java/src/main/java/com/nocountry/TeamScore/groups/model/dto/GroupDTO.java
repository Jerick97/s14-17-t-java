package com.nocountry.TeamScore.groups.model.dto;

import lombok.Data;

@Data
public class GroupDTO {
    private Long projectId; // esto es una FK pero de momento la dejo en Long hasta crear su correspondiente entidad
    private String name;
    private String description;
    private Integer status;
}
