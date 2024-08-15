package com.mghaas.feedbackapp.repositories;

import com.mghaas.feedbackapp.core.models.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findByName(String name);
}
