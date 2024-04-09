package com.nocountry.TeamScore.feedback.service;

import com.nocountry.TeamScore.feedback.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback create(Feedback feedback);
    Feedback update(Feedback feedback);

    Feedback getById(Long id);
    List<Feedback> getAll();

    void delete(Long id);
}