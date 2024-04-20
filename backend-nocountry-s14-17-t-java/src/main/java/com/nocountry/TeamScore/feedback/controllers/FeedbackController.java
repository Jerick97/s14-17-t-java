package com.nocountry.TeamScore.feedback.controllers;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.model.dto.FeedbackRequestDTO;
import com.nocountry.TeamScore.feedback.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/feedback")
@Tag(name = "Feedback", description = "Endpoints for feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @SecurityRequirement(name = "bearearAuth")
    @PostMapping
    @Operation(summary = "Create a new Feedback with all associations", description = "necesario tener grupo/s asignado/s a un proyecto, y al menos dos usuarios en el mismo grupo")
    public ResponseEntity<List<Feedback>> create(@RequestBody List<FeedbackRequestDTO> feedbackRequestDTOs) {
        return ResponseEntity.ok(feedbackService.create(feedbackRequestDTOs));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get feedback by id", description = "Return feedback by id")
    public ResponseEntity<Feedback> getById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getById(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary= "Update feedback", description = "Update a feedback/s segun el tamaño del array q se le pase")
    @PutMapping
    public ResponseEntity<List<Feedback>> update(@RequestBody FeedbackRequestDTO feedbackRequestDTO) {
        return ResponseEntity.ok(feedbackService.update(feedbackRequestDTO));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all feedback", description = "Return a list of all feedback")
    public ResponseEntity<List<FeedbackRequestDTO>> getAll() {
        return ResponseEntity.ok(feedbackService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*@SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hechosPorUser/{username}")
    @Operation(summary = "Get feedbacks by user", description = "Return feedbacks already done by username")
    public ResponseEntity<?> feedbacksHechosPorUser(@PathVariable String username) {
        return ResponseEntity.ok(feedbackService.feedbacksHechosPorUser(username));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hechosParaElUser/{username}")
    @Operation(summary = "Get feedbacks to user", description = "Return feedbacks done to username")
    public ResponseEntity<?> feedbacksHechosParaElUser(@PathVariable String username) {
        return ResponseEntity.ok(feedbackService.feedbacksHechosParaElUser(username));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/porGrupoAUnUsuario/{idGrupo}/{usuarioEvaluado}")
    @Operation(summary = "Get feedbacks by group to user", description = "Return feedbacks by group to user")
    public ResponseEntity<?> feedbacksPorGrupoAUnUsuario(@PathVariable Long idGrupo, @PathVariable String usuarioEvaluado) {
        return ResponseEntity.ok(feedbackService.feedbacksPorGrupoAUnUsuario(idGrupo, usuarioEvaluado));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hechosEnElGrupoPorElUsuario/{idGrupo}/{usuarioQEvalua}")
    @Operation(summary = "Get feedbacks by group of specific user", description = "Return feedbacks by group done by specific user")
    public ResponseEntity<?> feedbacksHechosEnElGrupoPorElUsuario(@PathVariable Long idGrupo, @PathVariable String usuarioQEvalua) {
        return ResponseEntity.ok(feedbackService.feedbacksHechosEnElGrupoPorElUsuario(idGrupo, usuarioQEvalua));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hechosPorElUsuarioParaElUsuario/{UsuarioQEvalua}/{usuarioEvaluado}/{idGrupo}")
    @Operation(summary = "Get feedbacks by specific user to other user", description = "Return feedbacks by specific user to other member of same team")
    public ResponseEntity<?> feedbacksHechosPorElUsuarioParaElUsuario(@PathVariable String UsuarioQEvalua, @PathVariable String usuarioEvaluado, @PathVariable Long idGrupo) {
        return ResponseEntity.ok(feedbackService.feedbacksHechosPorElUsuarioParaElUsuario(UsuarioQEvalua, usuarioEvaluado, idGrupo));
    }*/

    // faltarian enpoints para recuperar los feedback echos en un proyecto, o por un usuario
    // endpoint para recuperar el rol del usuario al que estoy evaluando
    // considerar responsabilidad del feedback saber cuantos feedback por proyecto hay y cuantos faltan completar en el grupo
}
