package com.nocountry.TeamScore.fieldsByRole.controller;

import com.nocountry.TeamScore.fieldsByRole.model.FieldsByRole;
import com.nocountry.TeamScore.fieldsByRole.service.FieldsByRoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/fieldsByRole")
public class FieldsByRoleController {
    @Autowired
    FieldsByRoleService fieldsByRoleService;
    @PostMapping
    public ResponseEntity<?> createFieldsByRole(@RequestBody FieldsByRole fieldsByRole){
        fieldsByRoleService.createFieldsByRole(fieldsByRole);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getFieldsByRole(@PathVariable Long id){
        try {
            return ResponseEntity.ok(fieldsByRoleService.getFieldsByRoleId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok("No se encontró el campo por rol con id: " + id);
        }
    }
    @GetMapping
    public ResponseEntity<Set<FieldsByRole>> getAllFieldsByRole() {
        return ResponseEntity.ok(fieldsByRoleService.getAllFieldsByRole());
    }
    @PutMapping()
    public ResponseEntity<Void> updateFieldsByRole(@RequestBody FieldsByRole fieldsByRole) {
        fieldsByRoleService.updateFieldsByRole(fieldsByRole);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFieldsByRole(@PathVariable Long id) {
        fieldsByRoleService.deleteFieldsByRole(id);
        return ResponseEntity.ok().build();
    }
}
