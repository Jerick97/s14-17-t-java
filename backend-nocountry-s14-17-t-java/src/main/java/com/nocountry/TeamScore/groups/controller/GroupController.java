package com.nocountry.TeamScore.groups.controller;

import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.dto.GroupDTO;
import com.nocountry.TeamScore.groups.service.GroupService;
import com.nocountry.TeamScore.security.user.model.dto.UsersInGroup;
import com.nocountry.TeamScore.security.user.util.ProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    @Autowired
    GroupService groupService;

    @Autowired
    ProgressService progressService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new empty Group", description = "This endpoint permits to create a new empty Group, necesario pasar el grupo vacio,borrar de project para abajo")
    public ResponseEntity<?> crearGrupoVacio(@RequestBody GroupDTO groupDTO) {
        groupService.crearGrupo(groupDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // un endpoint para crear grupos con usuarios al mismo tiempo, ver si conviene q cree usuarios por cascade, o que use usuarios existentes?
    // seria lo q se va a tratar en la tarea de importacion
    // tener en cuenta que el country por ahora es siempre argentina

    @Operation(summary = "trae un grupo y sus usuarios", description = "Importante, el grupo debe estar en un proyecto.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupAndUsersById(@PathVariable Long id) {
        Group group = groupService.getGroupById(id);

        if (group != null) {

            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setName(group.getName());
            groupDTO.setDescription(group.getDescription());
            groupDTO.setStatus(group.getStatus());
            groupDTO.setProjectId(group.getProjectId().getId());
            groupDTO.setUsuariosEnElGrupo(
                    group.getGroupByUserSet().stream()
                            .map(groupByUser -> UsersInGroup.fromGroupByUser(groupByUser, progressService))
                            .collect(Collectors.toSet())
            );
            return ResponseEntity.ok(groupDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{id}/asignarUsuario/{id_usuario}/con_rol/{rol}")
    @Operation(summary = "Assign a user to a group", description = "This endpoint assigns a user to a group")
    public ResponseEntity<?> asignarUsuarioAlGrupoConId(@PathVariable Long id, @PathVariable Long id_usuario, @PathVariable String rol) {
        return ResponseEntity.ok(groupService.asignarUsuarioAlGrupoConId(id, id_usuario, rol));
    }

    // más adelante se puede proponer un endpoint para asignar de a varios
}
