package com.nocountry.TeamScore.feedback.repository;

import com.nocountry.TeamScore.feedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

//    // Feedbacks hechos por un usuario
//    @Query("SELECT f FROM Feedback f WHERE f.usuarioQueEvalua.name = :username")
//    List<Feedback> findFeedbacksHechosPorUser(@Param("username") String username);
//
//    // Feedbacks hechos para un usuario
//    @Query("SELECT f FROM Feedback f WHERE f.usuarioEvaluado.user.name = :username")
//    List<Feedback> findFeedbacksHechosParaElUser(@Param("username") String username);
//
//    // Feedbacks de un grupo a un usuario
//    @Query("SELECT f FROM Feedback f JOIN f.proyectoEvaluado p JOIN p.projectId.groups g WHERE g.id = :idGrupo AND f.usuarioEvaluado.user.name = :usuarioEvaluado")
//    List<Feedback> findFeedbacksPorGrupoAUnUsuario(@Param("idGrupo") Long idGrupo, @Param("usuarioEvaluado") String usuarioEvaluado);
//
//    // Feedbacks hechos en el grupo por el usuario
//    @Query("SELECT f FROM Feedback f JOIN f.proyectoEvaluado p JOIN p.projectId.groups g WHERE g.id = :idGrupo AND f.usuarioQueEvalua.name = :usuarioQEvalua")
//    List<Feedback> findFeedbacksHechosEnElGrupoPorElUsuario(@Param("idGrupo") Long idGrupo, @Param("usuarioQEvalua") String usuarioQEvalua);
//
//    // Feedbacks hechos por el usuario para el usuario en un grupo
//    @Query("SELECT f FROM Feedback f JOIN f.proyectoEvaluado p JOIN p.projectId.groups g WHERE g.id = :idGrupo AND f.usuarioQueEvalua.name = :UsuarioQEvalua AND f.usuarioEvaluado.user.name = :usuarioEvaluado")
//    List<Feedback> findFeedbacksHechosPorElUsuarioParaElUsuario(@Param("UsuarioQEvalua") String UsuarioQEvalua, @Param("usuarioEvaluado") String usuarioEvaluado, @Param("idGrupo") Long idGrupo);

//     Por convencion de nombres, podria crearlo tmb asi:---------------------------------------------------------------
    // Feedbacks hechos por un usuario
    List<Feedback> findByUsuarioQueEvalua_Email(String email);

    // Feedbacks hechos para un usuario
    List<Feedback> findByUsuarioEvaluado_User_Email(String email);

    // Feedbacks de un grupo a un usuario
    List<Feedback> findByProyectoEvaluado_Group_IdAndUsuarioEvaluado_User_Email(Long idGrupo, String email);

    // Feedbacks hechos en el grupo por el usuario
    List<Feedback> findByProyectoEvaluado_Group_IdAndUsuarioQueEvalua_Email(Long idGrupo, String email);

    // Feedbacks hechos por el usuario para el usuario en un grupo
    List<Feedback> findByUsuarioQueEvalua_EmailAndUsuarioEvaluado_User_EmailAndProyectoEvaluado_Group_Id(String emailUsuarioQueEvalua, String emailUsuarioEvaluado, Long idGrupo);

}
