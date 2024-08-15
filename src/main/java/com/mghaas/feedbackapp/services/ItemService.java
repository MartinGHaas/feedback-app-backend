package com.mghaas.feedbackapp.services;

import com.mghaas.feedbackapp.core.models.feedback.FeedBack;
import com.mghaas.feedbackapp.core.models.feedback.FeedBackSearchResponseDTO;
import com.mghaas.feedbackapp.core.models.item.Item;
import com.mghaas.feedbackapp.core.models.item.ItemSearchResponseDTO;
import com.mghaas.feedbackapp.repositories.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemSearchResponseDTO createResponse(Item item)
            throws ResponseStatusException {

        var feedbacks = this.getFeedbackList(item);

        return new ItemSearchResponseDTO(
                item.getId(),
                item.getName(),
                feedbacks
        );
    }

    public Item findItemByName(String itemName)
            throws ResponseStatusException {
        return itemRepository.findByName(itemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<FeedBackSearchResponseDTO> getFeedbackList(Item item) {
        List<FeedBackSearchResponseDTO> feedbacks = new ArrayList<>();

        for (FeedBack feedBack : item.getFeedBacks()) {
            feedbacks.add(new FeedBackSearchResponseDTO(
                    feedBack.getId(),
                    feedBack.getComment(),
                    feedBack.getRating()
            ));
        }

        return feedbacks;
    }
}
