package com.nocountry.TeamScore.groups.model.dto;

import com.nocountry.TeamScore.projects.model.Project;
import lombok.Data;

@Data
public class GroupDTO { // para crear grupos vacios
    private String name;
    private String description;
    private Integer status;
}
