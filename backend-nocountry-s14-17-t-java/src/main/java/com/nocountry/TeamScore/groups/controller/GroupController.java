package com.nocountry.TeamScore.groups.controller;

import com.nocountry.TeamScore.groups.model.dto.GroupDTO;
import com.nocountry.TeamScore.groups.service.GroupService;
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
    public ResponseEntity<?> crearGrupoVacio(@RequestBody GroupDTO groupDTO) {
        groupService.crearGrupo(groupDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupDTO(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(groupService.getGrupo(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok("No se encontró el grupo con id: " + id);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{id}/asignarUsuario/{id_usuario}/con_rol/{rol}")
    public ResponseEntity<?> asignarUsuarioAlGrupoConId(@PathVariable Long id, @PathVariable Long id_usuario, @PathVariable String rol) {
        return ResponseEntity.ok(groupService.asignarUsuarioAlGrupoConId(id, id_usuario, rol));
    }

    // más adelante se puede proponer un endpoint para asignar de a varios
}
