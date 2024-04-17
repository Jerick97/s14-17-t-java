package com.nocountry.TeamScore.feedback.model;

import com.nocountry.TeamScore.groups.model.GroupByUser;
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
    @JoinColumn(name = "usuario_evaluado", referencedColumnName = "user_id") //  Muchos feedback pertenecen a un usuario evaluado
    @Schema(description = "User who is evaluated", example = "2")
    private GroupByUser usuarioEvaluado; // de aqui saco el rol y el rol del usuario que evaluo, verificar que el group_id del usuario evaluado sea el mismo q el del usuarioQueEvalua(que esten en el mismo grupo).
    //    @ManyToOne
//    @JoinColumn(name = "field_id")
    @Schema(description = "Field to evaluate", example = "5")
    private Long campoQueEvalua; // por ahora lo dejo como long cada campo a evaluar segun el rol
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @Schema(description = "Project to evaluate", example = "1")
    private Project proyectoEvaluado; // por ahora long, el proyecto al que pertenece el feedback// el field(campo) sabe a que proyecto pertenece no se si será necesario
    @Schema(description = "Value of feedback", example = "5")
    private Integer value;
    @Schema(description = "Comment of feedback", example = "comment...")
    private String comment;
    @Schema(description = "Date of feedback", example = "2022-01-01T00:00:00")
    private LocalDateTime dateTime;

}
