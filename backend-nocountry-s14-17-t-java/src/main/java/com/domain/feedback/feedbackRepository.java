package com.domain.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.feedback.feedback;

public interface feedbackRepository extends JpaRepository<feedback, Long> {
    
}
