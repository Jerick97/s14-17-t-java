package com.nocountry.TeamScore.fieldByProject.controller;

import com.nocountry.TeamScore.fieldByProject.model.FieldByProject;
import com.nocountry.TeamScore.fieldByProject.model.dto.FieldByProjectDTO;
import com.nocountry.TeamScore.fieldByProject.model.dto.FieldByProyectResponseDTO;
import com.nocountry.TeamScore.fieldByProject.service.FieldByProjectService;
import com.nocountry.TeamScore.fields.model.dto.FieldDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fielByProject")
public class FieldByProjectController {

    private FieldByProjectService fieldByProjectService;

    public FieldByProjectController(FieldByProjectService fieldByProjectService){
        this.fieldByProjectService = fieldByProjectService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createFieldByProject(@RequestBody FieldByProjectDTO fieldByProjectDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(fieldByProjectService.createFieldByProject(fieldByProjectDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong"+ e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateFieldByProject(@PathVariable Long id, @RequestBody FieldByProjectDTO fieldByProjectDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fieldByProjectService.updateFieldByProject(id, fieldByProjectDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFieldById(@PathVariable Long id) {
        try {
            FieldByProyectResponseDTO fieldByProject = fieldByProjectService.getFieldByProjectById(id);
            return ResponseEntity.status(HttpStatus.OK).body(fieldByProject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field by Project not found."+ e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFields() {
        try {
            List<FieldByProyectResponseDTO> fieldDTOS = fieldByProjectService.getAllFieldByProject();
            return ResponseEntity.status(HttpStatus.OK).body(fieldDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Something went wrong" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteField(@PathVariable Long id) {
        try {
            fieldByProjectService.deleteFieldByProject(id);
            return ResponseEntity.status(HttpStatus.OK).body("The data with ID " + id + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong while trying to delete the data with ID " + id + ": " + e.getMessage());
        }
    }

}
