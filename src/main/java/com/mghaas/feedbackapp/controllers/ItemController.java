package com.mghaas.feedbackapp.controllers;

import com.mghaas.feedbackapp.core.models.item.Item;
import com.mghaas.feedbackapp.core.models.item.ItemCreationRequestDTO;
import com.mghaas.feedbackapp.core.models.item.ItemSearchResponseDTO;
import com.mghaas.feedbackapp.repositories.ItemRepository;
import com.mghaas.feedbackapp.services.ItemService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("api/v1/items")
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    public ItemController(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @GetMapping("{name}")
    public ResponseEntity<ItemSearchResponseDTO> getItem(
            @PathVariable @NonNull String name
    ) {

        var item = itemService.findItemByName(name);
        return ResponseEntity.ok(itemService.createResponse(item));
    }

    @PostMapping("create")
    public ResponseEntity<Void> createItem(
            @RequestBody ItemCreationRequestDTO req
    ) throws ResponseStatusException {
        try {
            itemRepository.save(new Item(req.name()));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
