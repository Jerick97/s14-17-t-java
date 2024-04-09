package com.nocountry.TeamScore.feedback.controllers;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.service.FeedbackService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @SecurityRequirement(name = "bearearAuth")
    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.create(feedback));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(feedbackService.getById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok("No se encontró el feedback con id: " + id);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateFeedback(@Valid @RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.update(feedback));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<?> getAllFeedback(){ // esto devuelve todos los feedback sin importar de que usuario campo, o proyecto sean
        return ResponseEntity.ok(feedbackService.getAll());
    }

    // faltarian enpoints para recuperar los feedback echos en un proyecto, o por un usuario
    // endpoint para recuperar el rol del usuario al que estoy evaluando
    // considerar responsabilidad del feedback saber cuantos feedback por proyecto hay y cuantos faltan completar en el grupo
}
