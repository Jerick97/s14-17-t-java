package com.nocountry.TeamScore.feedback.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.feedback.model.dto.FeedbackRequestDTO;
import com.nocountry.TeamScore.feedback.repository.FeedbackRepository;
import com.nocountry.TeamScore.feedback.util.FeedbackMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper mapper;
    @Override
    public Feedback create(FeedbackRequestDTO feedbackRequestDTO) {
        return feedbackRepository.save(mapper.mapToFeedback(feedbackRequestDTO));
    }

    @Override
    public Feedback update(FeedbackRequestDTO feedbackRequestDTO) {
        return feedbackRepository.save(mapper.mapToFeedback(feedbackRequestDTO));
    }

    @Override
    public Feedback getById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("feedback not found"));
    }

    @Override
    public List<FeedbackRequestDTO> getAll() {
        return feedbackRepository.findAll().stream()
                .map(mapper::mapToFeedbackRequestDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }


}
