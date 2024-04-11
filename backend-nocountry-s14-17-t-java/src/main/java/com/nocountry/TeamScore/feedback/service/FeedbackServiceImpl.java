package com.nocountry.TeamScore.feedback.service;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.repository.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{

    private final FeedbackRepository feedbackRepository;
    @Override
    public Feedback create(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback update(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("feedback not found"));
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<Feedback> feedbacksHechosPorUser(String username) {
        return feedbackRepository.findByUsuarioQueEvalua_Email(username);
    }

    @Override
    public List<Feedback> feedbacksHechosParaElUser(String username) {
        return feedbackRepository.findByUsuarioEvaluado_User_Email(username);
    }

    @Override
    public List<Feedback> feedbacksPorGrupoAUnUsuario(Long idGrupo, String usuarioEvaluado) {
        return feedbackRepository.findByProyectoEvaluado_Group_IdAndUsuarioEvaluado_User_Email(idGrupo, usuarioEvaluado);
    }

    @Override
    public List<Feedback> feedbacksHechosEnElGrupoPorElUsuario(Long idGrupo, String usuarioQEvalua) {
        return feedbackRepository.findByProyectoEvaluado_Group_IdAndUsuarioQueEvalua_Email(idGrupo, usuarioQEvalua);
    }

    @Override
    public List<Feedback> feedbacksHechosPorElUsuarioParaElUsuario(String UsuarioQEvalua, String usuarioEvaluado, Long idGrupo) {
        return feedbackRepository.findByUsuarioQueEvalua_EmailAndUsuarioEvaluado_User_EmailAndProyectoEvaluado_Group_Id(UsuarioQEvalua, usuarioEvaluado, idGrupo);
    }

}
