package com.nocountry.TeamScore.feedback.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FeedbackRequestDTO {
    private String projectId;
    private String idUsuarioQueEvalua;
    private String idUsuarioEvaluado;
    private List<ValorDelFeedbackDTO> valorDelFeedback;

}

