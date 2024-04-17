package com.nocountry.TeamScore.feedback.model.dto;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.groups.repository.GroupByUserRepository;
import com.nocountry.TeamScore.groups.service.GroupService;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.projects.service.ProjectService;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
public class FeedbackRequestDTO {

    @Autowired
    private static UserService userService;
    @Autowired
    private static GroupByUserRepository groupByUserRepository;
    @Autowired
    private static ProjectService projectService;
    @Autowired
    private static GroupService groupService;

    private Long idUsuarioQueEvalua;
    private Long idUsuarioEvaluado;
    private Long idGrupoAlQuePertenecen;
    private Long idDelCampoEvaluado;
    private Long idProyectoEvaluado;
    private Integer valorDelFeedback;
    private String comentarios;
    private LocalDateTime dateTime;

    public static Feedback convertToFeedback(FeedbackRequestDTO feedbackRequestDTO) {
        Feedback feedback = new Feedback();

        User usuarioQueEvalua = userService.getByID(feedbackRequestDTO.getIdUsuarioQueEvalua());
        feedback.setUsuarioQueEvalua(usuarioQueEvalua);

        User usuarioEvaluado = userService.getByID(feedbackRequestDTO.getIdUsuarioEvaluado());
        Group grupoEvaluado = groupService.getGroupById(feedbackRequestDTO.getIdGrupoAlQuePertenecen());
        GroupByUser grupoDelEvaluado = groupByUserRepository.findByUserIdAndGroupId(feedbackRequestDTO.idUsuarioEvaluado, feedbackRequestDTO.getIdGrupoAlQuePertenecen());
        GroupByUser grupoDelEvaluador = groupByUserRepository.findByUserIdAndGroupId(feedbackRequestDTO.idUsuarioQueEvalua, feedbackRequestDTO.getIdGrupoAlQuePertenecen());

        if (grupoDelEvaluado.equals(grupoDelEvaluador)){ // estan en el mismo grupo lo cual es correcto
            feedback.setUsuarioEvaluado(grupoDelEvaluado);
        }

        feedback.setCampoQueEvalua(feedbackRequestDTO.getIdDelCampoEvaluado());

        Project proyectoEvaluado = projectService.findById(feedbackRequestDTO.getIdProyectoEvaluado());
        feedback.setProyectoEvaluado(proyectoEvaluado);

        feedback.setValue(feedbackRequestDTO.getValorDelFeedback());
        feedback.setComment(feedbackRequestDTO.getComentarios());
        feedback.setDateTime(feedbackRequestDTO.getDateTime());
        return feedback;
    }

    public static FeedbackRequestDTO convertToFeedbackDTO(Feedback feedback) {
        FeedbackRequestDTO feedbackRequestDTO = new FeedbackRequestDTO();
        feedbackRequestDTO.setIdUsuarioQueEvalua(feedback.getUsuarioQueEvalua().getId());
        feedbackRequestDTO.setIdUsuarioEvaluado(feedback.getUsuarioEvaluado().getId());
        feedbackRequestDTO.setIdDelCampoEvaluado(feedback.getCampoQueEvalua());
        feedbackRequestDTO.setIdProyectoEvaluado(feedback.getProyectoEvaluado().getId());
        feedbackRequestDTO.setValorDelFeedback(feedback.getValue());
        feedbackRequestDTO.setComentarios(feedback.getComment());
        feedbackRequestDTO.setDateTime(feedback.getDateTime());
        return feedbackRequestDTO;
    }
}
