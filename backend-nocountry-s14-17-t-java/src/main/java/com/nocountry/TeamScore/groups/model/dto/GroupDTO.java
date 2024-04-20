package com.nocountry.TeamScore.groups.model.dto;

import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.security.user.model.dto.UsersInGroup;
import lombok.Data;

import java.util.Set;

@Data
public class GroupDTO { // para crear grupos vacios pasar un array vacio, o no pasarlo
    private String name;
    private String description;
    private Integer status;
    private Set<UsersInGroup> usuariosEnElGrupo;
}
