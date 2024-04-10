package com.nocountry.TeamScore.feedback.service;

import com.nocountry.TeamScore.feedback.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback create(Feedback feedback);
    Feedback update(Feedback feedback);

    Feedback getById(Long id);
    List<Feedback> getAll();

    void delete(Long id);

    List<Feedback> feedbacksHechosPorUser(String username);
    List<Feedback> feedbacksHechosParaElUser(String username);
    List<Feedback> feedbacksPorGrupoAUnUsuario(Long idGrupo, String usuarioEvaluado);
    List<Feedback> feedbacksHechosEnElGrupoPorElUsuario(Long idGrupo, String usuarioQEvalua);
    List<Feedback> feedbacksHechosPorElUsuarioParaElUsuario(String UsuarioQEvalua, String usuarioEvaluado, Long idGrupo);
}