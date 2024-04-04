package com.domain.feedback;

import java.util.List;
import com.dto.FeedbackModel;

public interface FeedbackService {

    FeedbackModel createFeedback(FeedbackModel feedbackModel);
    FeedbackModel updateFeedback(Long feedbackId, FeedbackModel feedbackModel);

    FeedbackModel getFeedbackById(Long id);
    List<FeedbackModel> getAllFeedback();

    void deleteFeedback(Long feedbackId);
}
