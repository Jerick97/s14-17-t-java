package com.nocountry.TeamScore.roles.controllers;

import com.nocountry.TeamScore.roles.model.dto.RolesDTO;
import com.nocountry.TeamScore.roles.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// es un controlador con anotacion Maping para solicitudes Http
@RestController
@RequestMapping("/roles") // para toda solicitud que empiece con '/roles a este controlador
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController() {
        this(null);
    }

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesDTO> findRoleById(@PathVariable Long id) {
        RolesDTO roleDTO = roleRepository.findById(id);
        if (roleDTO != null) {
            return ResponseEntity.ok(roleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<RolesDTO> findAllRoles() {
        return roleRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<RolesDTO> createRole(@RequestBody RolesDTO roleDTO) {
        RolesDTO savedRole = roleRepository.save(roleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesDTO> updateRole(@PathVariable Long id, @RequestBody RolesDTO roleDTO) {
        // verificar si el rol existe
        if (roleRepository.findById(id) != null) {
            roleDTO.setId(id);
            RolesDTO updatedRole = roleRepository.update(roleDTO);
            return ResponseEntity.ok(updatedRole);
        } else {
            // manejar el caso en el que el rol no existe
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        // verificar si el rol existe
        if (roleRepository.findById(id) != null) {
            roleRepository.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            // manejar el caso en el que el rol no existe
            return ResponseEntity.notFound().build(); //devuelve el responseEntity con lso correspondientes codigos de estado HTTP
        }
    }
}

