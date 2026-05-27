package com.example.samdiclass1.domain.item.dto.request;

public record CreateItemRequest (
        String name,
        String type,
        int attackPower,
        int durability
) {
}
