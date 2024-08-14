package com.mghaas.feedbackapp.repositories;

import com.mghaas.feedbackapp.core.models.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Item findByName(String name);
}
