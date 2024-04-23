package com.nocountry.TeamScore.feedback.repository;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    boolean existsByUsuarioQueEvaluaAndUsuarioEvaluado(User evaluador, User user);
}
