package com.nocountry.TeamScore.feedback.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.model.dto.FeedbackRequestDTO;
import com.nocountry.TeamScore.feedback.repository.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{

    private final FeedbackRepository feedbackRepository;
    private final ObjectMapper mapper;
    @Override
    public Feedback create(FeedbackRequestDTO feedbackRequestDTO) {
        return feedbackRepository.save(FeedbackRequestDTO.convertToFeedback(feedbackRequestDTO));
    }

    @Override
    public Feedback update(FeedbackRequestDTO feedbackRequestDTO) {
        return feedbackRepository.save(FeedbackRequestDTO.convertToFeedback(feedbackRequestDTO));
    }

    @Override
    public Feedback getById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("feedback not found"));
    }

    @Override
    public List<FeedbackRequestDTO> getAll() {
        return feedbackRepository.findAll().stream()
                .map(FeedbackRequestDTO::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<FeedbackRequestDTO> feedbacksHechosPorUser(String username) {
        return feedbackRepository.findByUsuarioQueEvaluaEmail(username).stream()
                .map(FeedbackRequestDTO::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackRequestDTO> feedbacksHechosParaElUser(String username) {
        return feedbackRepository.findByUsuarioEvaluadoUserEmail(username).stream()
                .map(FeedbackRequestDTO::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackRequestDTO> feedbacksPorGrupoAUnUsuario(Long idGrupo, String usuarioEvaluado) {
        return feedbackRepository.findByProyectoEvaluado_Group_IdAndUsuarioEvaluado_User_Email(idGrupo, usuarioEvaluado).stream()
                .map(FeedbackRequestDTO::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackRequestDTO> feedbacksHechosEnElGrupoPorElUsuario(Long idGrupo, String usuarioQEvalua) {
        return feedbackRepository.findByProyectoEvaluado_Group_IdAndUsuarioQueEvalua_Email(idGrupo, usuarioQEvalua).stream()
                .map(FeedbackRequestDTO::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackRequestDTO> feedbacksHechosPorElUsuarioParaElUsuario(String UsuarioQEvalua, String usuarioEvaluado, Long idGrupo) {
        return feedbackRepository.findByUsuarioQueEvalua_EmailAndUsuarioEvaluado_User_EmailAndProyectoEvaluado_Group_Id(UsuarioQEvalua, usuarioEvaluado, idGrupo).stream()
                .map(FeedbackRequestDTO::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

}
