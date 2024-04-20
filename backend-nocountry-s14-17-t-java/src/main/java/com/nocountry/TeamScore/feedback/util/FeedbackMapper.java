package com.nocountry.TeamScore.feedback.util;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.model.dto.FeedbackRequestDTO;
import com.nocountry.TeamScore.feedback.model.dto.ValorDelFeedbackDTO;
import com.nocountry.TeamScore.fields.model.Field;
import com.nocountry.TeamScore.fields.repository.FieldRepository;
import com.nocountry.TeamScore.fields.service.FieldService;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.repository.ProjectRepository;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.repository.UserRepository;
import com.nocountry.TeamScore.security.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackMapper {

    private final UserService userService;
    private final FieldRepository fieldService;
    private final ProjectRepository projectService;
    public Feedback mapToFeedback(FeedbackRequestDTO feedbackRequestDTO, ValorDelFeedbackDTO valorDelFeedbackDTO) {
        Feedback feedback = new Feedback();
        User usuarioQueEvalua = userService.getByID(Long.valueOf(feedbackRequestDTO.getIdUsuarioQueEvalua()));
        User usuarioEvaluado = userService.getByID(Long.valueOf(feedbackRequestDTO.getIdUsuarioEvaluado()));
        Field field = fieldService.findById(valorDelFeedbackDTO.getField_id()).orElseThrow();
        Project project = projectService.findById(Long.valueOf(feedbackRequestDTO.getProjectId())).orElseThrow();

        feedback.setUsuarioQueEvalua(usuarioQueEvalua);
        feedback.setUsuarioEvaluado(usuarioEvaluado);
        feedback.setField(field);
        feedback.setProyectoEvaluado(project);
        feedback.setValue(valorDelFeedbackDTO.getScore());
        feedback.setComment(valorDelFeedbackDTO.getComments());
        feedback.setDateTime(LocalDateTime.now()); // Establecemos la fecha y hora actuales

        return feedback;
    }

    public FeedbackRequestDTO mapToFeedbackRequestDTO(Feedback feedback) {
        FeedbackRequestDTO feedbackRequestDTO = new FeedbackRequestDTO();
        feedbackRequestDTO.setIdUsuarioQueEvalua(feedback.getUsuarioQueEvalua().getId().toString());
        feedbackRequestDTO.setIdUsuarioEvaluado(feedback.getUsuarioEvaluado().getId().toString());
        feedbackRequestDTO.setProjectId(feedback.getProyectoEvaluado().getId().toString());
        ValorDelFeedbackDTO valorDelFeedbackDTO = new ValorDelFeedbackDTO();
        valorDelFeedbackDTO.setField_id(feedback.getField().getId());
        valorDelFeedbackDTO.setScore(feedback.getValue());
        valorDelFeedbackDTO.setComments(feedback.getComment());
        feedbackRequestDTO.setValorDelFeedback(List.of(valorDelFeedbackDTO));

        return feedbackRequestDTO;
    }

}

