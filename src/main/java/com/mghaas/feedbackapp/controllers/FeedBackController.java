package com.mghaas.feedbackapp.controllers;

import com.mghaas.feedbackapp.core.models.feedback.FeedbackCreationRequestDTO;
import com.mghaas.feedbackapp.services.FeedBackService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/feedback")
public class FeedBackController {

    private final FeedBackService feedBackService;

    public FeedBackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping("{itemName}")
    public ResponseEntity<Void> feedback(
            @RequestBody FeedbackCreationRequestDTO req,
            @PathVariable String itemName
    ) {
        feedBackService.feedback(itemName, req.comment(), req.rating());
        return ResponseEntity.ok().build();
    }
}
