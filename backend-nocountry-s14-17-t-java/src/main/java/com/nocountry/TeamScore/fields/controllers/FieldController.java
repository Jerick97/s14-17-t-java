package com.nocountry.TeamScore.fields.controllers;

import com.nocountry.TeamScore.fields.model.dto.FieldDTO;
import com.nocountry.TeamScore.fields.service.FieldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/field")
@Tag(name = "Field", description = "Endpoints for managing Fields")
public class FieldController {

    private FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new Field", description = "This endpoint permits to create a new Field")
    public ResponseEntity<?> createField(@RequestBody FieldDTO fieldDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(fieldService.createField(fieldDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong"+ e.getMessage());
        }
    }


    @PutMapping(value = "/{fieldId}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a Field", description = "This endpoint permits to update a Field")
    public ResponseEntity<?> updateField(@PathVariable Long fieldId, @RequestBody FieldDTO fieldDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fieldService.updateField(fieldId, fieldDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong"+ e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Field by id", description = "This endpoint returns a Field by its id")
    public ResponseEntity<?> getFieldById(@PathVariable Long id) {
        try {
            FieldDTO fieldDTO = fieldService.getFieldById(id);
            return ResponseEntity.status(HttpStatus.OK).body(fieldDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field not found."+ e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Get all Fields", description = "This endpoint returns a list of all Fields")
    public ResponseEntity<?> getAllFields() {
        try {
            List<FieldDTO> fieldDTOS = fieldService.getAllfields();
            return ResponseEntity.status(HttpStatus.OK).body(fieldDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error! Something went wrong"+ e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Field by id", description = "This endpoint deletes a Field by its id")
    public ResponseEntity<String> deleteField(@PathVariable Long id) {
        try {
            fieldService.deleteField(id);
            return ResponseEntity.status(HttpStatus.OK).body("The data with ID " + id + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong while trying to delete the data with ID " + id + ": " + e.getMessage());
        }
    }
}
