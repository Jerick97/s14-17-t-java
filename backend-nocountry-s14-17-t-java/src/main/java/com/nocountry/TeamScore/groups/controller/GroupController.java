package com.nocountry.TeamScore.groups.controller;

import com.nocountry.TeamScore.groups.model.dto.GroupDTO;
import com.nocountry.TeamScore.groups.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    @Autowired
    GroupService groupService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new Group", description = "This endpoint permits to create a new Group")
    public ResponseEntity<?> crearGrupoVacio(@RequestBody GroupDTO groupDTO) {
        groupService.crearGrupo(groupDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a Group by id", description = "This endpoint returns a Group by its id")
    public ResponseEntity<?> getGroupDTO(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(groupService.getGrupo(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok("No se encontró el grupo con id: " + id);
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
