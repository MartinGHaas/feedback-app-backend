package com.mghaas.feedbackapp.services;

import com.mghaas.feedbackapp.core.models.feedback.FeedBack;
import com.mghaas.feedbackapp.core.models.item.Item;
import com.mghaas.feedbackapp.repositories.FeedBackRepository;
import com.mghaas.feedbackapp.repositories.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FeedBackService {

    private final ItemRepository itemRepository;
    private final FeedBackRepository feedBackRepository;

    public FeedBackService(ItemRepository itemRepository, FeedBackRepository feedBackRepository) {
        this.itemRepository = itemRepository;
        this.feedBackRepository = feedBackRepository;
    }

    public void feedback(String itemName, String comment, byte rating)
            throws ResponseStatusException {

        if (rating > 5 || rating < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Item item = itemRepository.findByName(itemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        feedBackRepository.save(new FeedBack(comment, rating, item));
    }
}
