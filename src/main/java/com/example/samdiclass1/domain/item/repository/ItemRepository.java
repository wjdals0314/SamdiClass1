package com.example.samdiclass1.domain.item.repository;

import com.example.samdiclass1.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
    // Optional<>은 값이 있을 수도 없을 수도 있음
}
