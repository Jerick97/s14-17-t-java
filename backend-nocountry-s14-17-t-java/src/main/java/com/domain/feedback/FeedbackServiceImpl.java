package com.domain.feedback;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.FeedbackModel;
import com.domain.feedback.Feedback;
import com.domain.feedback.FeedbackRepository;
import com.domain.feedback.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    private final ModelMapper modelMapper;
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(
        ModelMapper modelMapper,
        FeedbackRepository feedbackRepository
    ) {
        this.modelMapper = modelMapper;
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    @Override
    public FeedbackModel createFeedback(FeedbackModel feedbackModel) {
        
        Feedback feedback = modelMapper.map(feedbackModel, Feedback.class);
        feedback = feedbackRepository.save(feedback);
        feedbackModel.setId(feedback.getId());
        return modelMapper.map(feedback, FeedbackModel.class);
    }

    @Transactional
    @Override
    public FeedbackModel updateFeedback(Long id, FeedbackModel feedbackModel) {

        if (feedbackRepository.existsById(id)) {
            Feedback feedbackActualizado = modelMapper.map(feedbackModel, Feedback.class);
            feedbackRepository.save(feedbackActualizado);
            return feedbackModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Feedback no encontrado para actualizar."
        );
    }

    @Override
    public FeedbackModel getFeedbackById(Long id) {

        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Feedback no encontrado."
            );
        }
        return modelMapper.map(feedback, FeedbackModel.class);
    }

    @Override
    public List<FeedbackModel> getAllFeedback() {

        List<Feedback> entity = feedbackRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, FeedbackModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteFeedback(Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Feedback no encontrada para eliminar."
            );
        }
    }
}
