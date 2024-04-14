package com.nocountry.TeamScore.fields.controllers;

import com.nocountry.TeamScore.fields.model.dto.FieldDTO;
import com.nocountry.TeamScore.fields.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/field")
public class FieldController {

    private FieldService fieldService;

    public FieldController(FieldService fieldService) {

        this.fieldService = fieldService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createField(@RequestBody FieldDTO fieldDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(fieldService.createField(fieldDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong"+ e.getMessage());
        }
    }


    @PutMapping(value = "/{fieldId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateField(@PathVariable Long fieldId, @RequestBody FieldDTO fieldDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fieldService.updateField(fieldId, fieldDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong"+ e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFieldById(@PathVariable Long id) {
        try {
            FieldDTO fieldDTO = fieldService.getFieldById(id);
            return ResponseEntity.status(HttpStatus.OK).body(fieldDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field not found."+ e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFields() {
        try {
            List<FieldDTO> fieldDTOS = fieldService.getAllfields();
            return ResponseEntity.status(HttpStatus.OK).body(fieldDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error! Something went wrong"+ e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteField(@PathVariable Long id) {
        try {
            fieldService.deleteField(id);
            return ResponseEntity.status(HttpStatus.OK).body("The data with ID " + id + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong while trying to delete the data with ID " + id + ": " + e.getMessage());
        }
    }
}
