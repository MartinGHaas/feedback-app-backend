package com.mghaas.feedbackapp.repositories;

import com.mghaas.feedbackapp.core.models.feedback.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, String> {
}
