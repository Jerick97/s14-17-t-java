package com.domain.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.feedback.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
}