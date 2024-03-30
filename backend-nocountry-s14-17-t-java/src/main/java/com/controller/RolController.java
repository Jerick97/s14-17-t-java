package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.RolModel;
import com.domain.rol.RolService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/rol")
public class RolController {

    private RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<RolModel> createRol(@RequestBody RolModel rolModel) {
        return ResponseEntity.ok( this.rolService.createRol(rolModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolModel> updateRol(@PathVariable Long id, @RequestBody RolModel rolModel) {
        return ResponseEntity.ok( this.rolService.updateRol(id, rolModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolModel> getRolById(@PathVariable Long id) {
        return ResponseEntity.ok( this.rolService.getRolById(id) );
    }

    @GetMapping
    public ResponseEntity<List<RolModel>> getAllRols() {
        return ResponseEntity.ok( this.rolService.getAllRols() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }
}
