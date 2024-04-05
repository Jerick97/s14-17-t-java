package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.FieldsModel;
import com.domain.fields.FieldsService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/fields")
public class FieldsController {

    private FieldsService fieldsService;

    public FieldsController(FieldsService fieldsService) {
        this.fieldsService = fieldsService;
    }

    @PostMapping
    public ResponseEntity<FieldsModel> createFields(@RequestBody FieldsModel fieldsModel) {
        return ResponseEntity.ok( this.fieldsService.createFields(fieldsModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldsModel> updateFields(@PathVariable Long id, @RequestBody FieldsModel fieldsModel) {
        return ResponseEntity.ok( this.fieldsService.updateFields(id, fieldsModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldsModel> getFieldsById(@PathVariable Long id) {
        return ResponseEntity.ok( this.fieldsService.getFieldsById(id) );
    }

    @GetMapping
    public ResponseEntity<List<FieldsModel>> getAllFields() {
        return ResponseEntity.ok( this.fieldsService.getAllFields() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFields(@PathVariable Long id) {
        fieldsService.deleteFields(id);
        return ResponseEntity.noContent().build();
    }
}
