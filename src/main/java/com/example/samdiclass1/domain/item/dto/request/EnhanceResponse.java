package com.example.samdiclass1.domain.item.dto.request;

public record EnhanceResponse(
        String name,
        boolean success,
        int attackPower,
        int durability,
        int enhanceLevel,
        String message
) {
}
