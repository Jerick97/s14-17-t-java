package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.FeedbackModel;
import com.domain.feedback.FeedbackService;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/feedback")
public class FeedbackController {

    private FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackModel> createFeedback(@RequestBody FeedbackModel feedbackModel) {
        return ResponseEntity.ok( this.feedbackService.createFeedback(feedbackModel) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackModel> updateFeedback(@PathVariable Long id, @RequestBody FeedbackModel feedbackModel) {
        return ResponseEntity.ok( this.feedbackService.updateFeedback(id, feedbackModel) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackModel> getFeedbackById(@PathVariable Long id) {
        return ResponseEntity.ok( this.feedbackService.getFeedbackById(id) );
    }

    @GetMapping
    public ResponseEntity<List<FeedbackModel>> getAllFeedback() {
        return ResponseEntity.ok( this.feedbackService.getAllFeedback() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
