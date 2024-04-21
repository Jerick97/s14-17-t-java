package com.nocountry.TeamScore.security.user.util;

import com.nocountry.TeamScore.feedback.repository.FeedbackRepository;
import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.security.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {

    private final FeedbackRepository feedbackRepository;

    public ProgressService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Integer calculateProgress(User user, Group group) {
        Long feedbackCount = feedbackRepository.countDistinctUsuarioEvaluadoByUsuarioQueEvalua(user);
        int groupSize = group.getUsersEnabled().size() - 1; // resto el usuario que evalua

        // Falta tener en cuenta que el feedback hay varios segun la cantidad de fields
        return (int) ((double) feedbackCount / groupSize * 100);
    }
}

