package com.mghaas.feedbackapp.controllers;

import com.mghaas.feedbackapp.core.models.feedback.FeedBack;
import com.mghaas.feedbackapp.core.models.feedback.FeedBackSearchResponseDTO;
import com.mghaas.feedbackapp.core.models.item.Item;
import com.mghaas.feedbackapp.core.models.item.ItemCreationRequestDTO;
import com.mghaas.feedbackapp.core.models.item.ItemSearchResponseDTO;
import com.mghaas.feedbackapp.repositories.ItemRepository;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api/v1/items")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("{name}")
    public ResponseEntity<ItemSearchResponseDTO> createItem(
            @PathVariable @NonNull String name
    ) {
        Item item = itemRepository.findByName(name);

        if(item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<FeedBackSearchResponseDTO> feedbacks = new ArrayList<>();

        for (FeedBack feedBack : item.getFeedBacks()) {
            feedbacks.add(new FeedBackSearchResponseDTO(
                    feedBack.getId(),
                    feedBack.getComment(),
                    feedBack.getRating()
            ));
        }

        return ResponseEntity.ok(new ItemSearchResponseDTO(
                item.getId(),
                item.getName(),
                feedbacks
        ));
    }

    @PostMapping("create")
    public ResponseEntity<Void> createItem(
            @RequestBody ItemCreationRequestDTO req
    ) {
        try {
            itemRepository.save(new Item(req.name()));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
