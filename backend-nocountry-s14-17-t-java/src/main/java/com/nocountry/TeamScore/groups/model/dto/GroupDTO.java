package com.nocountry.TeamScore.groups.model.dto;

import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.security.user.model.dto.UsersInGroup;
import lombok.Data;

import java.util.Set;

@Data
public class GroupDTO { // para crear grupos vacios pasar un array vacio, o no pasarlo
    private String name;
    private String description;
    private Integer status;
    private Long projectId;
    private Set<UsersInGroup> usuariosEnElGrupo;

    public static GroupDTO convertToDto(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName(group.getName());
        groupDTO.setDescription(group.getDescription());
        groupDTO.setStatus(group.getStatus());
        groupDTO.setProjectId(group.getProjectId().getId());
        // Aquí iría el código para mapear los usuarios en el grupo
        return groupDTO;
    }

    // Este seria un metodo a considerar sino quiero crear grupos vacios, sino que quiero que tengan proyectos y usuarios asignados al momento de crearlos
    // Tener en cuenta que habria que crear un servicio, como el caso del convert de usuarios.
//    public Group convertToEntity(GroupDTO groupDTO) {
//        Group group = new Group();
//        group.setName(groupDTO.getName());
//        group.setDescription(groupDTO.getDescription());
//        group.setStatus(groupDTO.getStatus());
//        Project project = projectService.findById(groupDTO.getProjectId());
//        if (project != null) {
//            group.setProjectId(project);
//        }
//        // Aquí iría el código para mapear los usuarios en el grupo
//        return group;
//    }


}
