package com.nocountry.TeamScore.feedback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.security.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Feedback {

    @Id
    @GeneratedValue
    @Schema(description = "Id feedback", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_que_evalua")
    @Schema(description = "User who evaluates", example = "1")
    private User usuarioQueEvalua; // aca es mucho por que se da feedback de muchos campos para un usuario

    @ManyToOne
    @JoinColumn(name = "user_evaluado")
    @Schema(description = "User evaluated", example = "1")
    private User usuarioEvaluado;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @Schema(description = "Project to evaluate", example = "1")
    private Project proyectoEvaluado; // por ahora long, el proyecto al que pertenece el feedback// el field(campo) sabe a que proyecto pertenece no se si será necesario

    @Schema(description = "Value of feedback", example = "5")
    private Integer value;

    @Schema(description = "Comment of feedback", example = "comment...")
    private String comment;

    @Schema(description = "Date of feedback", example = "2022-01-01T00:00:00")
    private LocalDateTime dateTime;

}
