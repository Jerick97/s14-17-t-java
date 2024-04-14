package com.nocountry.TeamScore.feedback.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackRequestDTO {

    private Long idUsuarioQueEvalua;
    private Long idUsuarioEvaluado;
    private Long idProyectoEvaluado;
    private Integer valorDelFeedback;
    private String comentarios;
    private LocalDateTime dateTime;

}
