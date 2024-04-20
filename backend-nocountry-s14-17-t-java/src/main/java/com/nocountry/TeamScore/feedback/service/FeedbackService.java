package com.nocountry.TeamScore.feedback.service;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.model.dto.FeedbackRequestDTO;

import java.util.List;

public interface FeedbackService {
    List<Feedback> create(List<FeedbackRequestDTO> feedbackRequestDTOs);
    List<Feedback> update(FeedbackRequestDTO feedbackRequestDTO);

    Feedback getById(Long id);
    List<FeedbackRequestDTO> getAll();

    void delete(Long id);

}