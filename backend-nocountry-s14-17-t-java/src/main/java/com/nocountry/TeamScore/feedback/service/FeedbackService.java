package com.nocountry.TeamScore.feedback.service;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.model.dto.FeedbackRequestDTO;

import java.util.List;

public interface FeedbackService {
    Feedback create(FeedbackRequestDTO feedback);
    Feedback update(FeedbackRequestDTO feedback);

    Feedback getById(Long id);
    List<FeedbackRequestDTO> getAll();

    void delete(Long id);

}