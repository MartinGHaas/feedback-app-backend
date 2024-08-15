package com.mghaas.feedbackapp.controllers;

import com.mghaas.feedbackapp.core.models.feedback.FeedBack;
import com.mghaas.feedbackapp.core.models.feedback.FeedbackCreationRequestDTO;
import com.mghaas.feedbackapp.core.models.item.Item;
import com.mghaas.feedbackapp.repositories.FeedBackRepository;
import com.mghaas.feedbackapp.repositories.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("api/v1/feedback")
public class FeedBackController {

    private final ItemRepository itemRepository;
    private final FeedBackRepository feedBackRepository;

    public FeedBackController(ItemRepository itemRepository, FeedBackRepository feedBackRepository) {
        this.itemRepository = itemRepository;
        this.feedBackRepository = feedBackRepository;
    }

    @PostMapping("{itemName}")
    public ResponseEntity<Void> feedback(
            @RequestBody FeedbackCreationRequestDTO req,
            @PathVariable String itemName
    ) {
        Item item = itemRepository.findByName(itemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        FeedBack feedBack = new FeedBack(req.comment(), req.rating(), item);
        feedBackRepository.save(feedBack);

        return ResponseEntity.ok().build();
    }
}
