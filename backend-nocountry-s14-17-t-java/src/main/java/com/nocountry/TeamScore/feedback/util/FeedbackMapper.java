package com.nocountry.TeamScore.feedback.util;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.model.dto.FeedbackRequestDTO;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.repository.ProjectRepository;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Feedback mapToFeedback(FeedbackRequestDTO dto) {
        User usuarioQueEvalua = userRepository.findById(dto.getIdUsuarioQueEvalua())
                .orElseThrow(() -> new EntityNotFoundException("Userio que evalua not found"));
        User usuarioEvaluado = userRepository.findById(dto.getIdUsuarioEvaluado())
                .orElseThrow(() -> new EntityNotFoundException("Usuario evaluado not found"));
        Project proyectoEvaluado = projectRepository.findById(dto.getIdProyectoEvaluado())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        Feedback feedback = new Feedback();
        feedback.setUsuarioQueEvalua(usuarioQueEvalua);
        feedback.setUsuarioEvaluado(usuarioEvaluado);
        feedback.setProyectoEvaluado(proyectoEvaluado);
        feedback.setValue(dto.getValorDelFeedback());
        feedback.setComment(dto.getComentarios());
        feedback.setDateTime(dto.getDateTime());

        return feedback;
    }

    public FeedbackRequestDTO mapToFeedbackRequestDTO(Feedback feedback) {
        FeedbackRequestDTO dto = new FeedbackRequestDTO();
        dto.setIdUsuarioQueEvalua(feedback.getUsuarioQueEvalua().getId());
        dto.setIdUsuarioEvaluado(feedback.getUsuarioEvaluado().getId());
        dto.setIdProyectoEvaluado(feedback.getProyectoEvaluado().getId());
        dto.setValorDelFeedback(feedback.getValue());
        dto.setComentarios(feedback.getComment());
        dto.setDateTime(feedback.getDateTime());

        return dto;
    }

}
