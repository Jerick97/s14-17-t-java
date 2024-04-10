package com.nocountry.TeamScore.feedback.model;

import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.security.user.model.User;
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
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_que_evalua")
    private User usuarioQueEvalua; // aca es mucho por que se da feedback de muchos campos para un usuario
    @ManyToOne
    @JoinColumn(name = "usuario_evaluado", referencedColumnName = "user_id") //  Muchos feedback pertenecen a un usuario evaluado
    private GroupByUser usuarioEvaluado; // de aqui saco el rol y el rol del usuario que evaluo, verificar que el group_id del usuario evaluado sea el mismo q el del usuarioQueEvalua(que esten en el mismo grupo).
    //    @ManyToOne
//    @JoinColumn(name = "field_id")
    private Long campoQueEvalua; // por ahora lo dejo como long cada campo a evaluar segun el rol
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project proyectoEvaluado; // por ahora long, el proyecto al que pertenece el feedback// el field(campo) sabe a que proyecto pertenece no se si será necesario
    private Integer value;
    private String comment;
    private LocalDateTime dateTime;

}
