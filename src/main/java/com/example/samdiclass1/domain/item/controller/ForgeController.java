package com.example.samdiclass1.domain.item.controller;


import com.example.samdiclass1.domain.item.dto.request.CreateItemRequest;
import com.example.samdiclass1.domain.item.dto.request.EnhanceRequest;
import com.example.samdiclass1.domain.item.dto.request.EnhanceResponse;
import com.example.samdiclass1.domain.item.dto.request.UpdateItem;
import com.example.samdiclass1.domain.item.dto.response.ItemResponse;
import com.example.samdiclass1.domain.item.service.ForgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forge")
@RequiredArgsConstructor
@Tag(name = "아이템 API", description = "아이템 관련 API")
public class ForgeController {
    private final ForgeService forgeService;

    @Operation(summary = "아이템 생성")
    @PostMapping
    public void createItem(@RequestBody CreateItemRequest request) {
        forgeService.creamItem(request);
    }
    @GetMapping
    public ItemResponse findItemByName(@RequestParam String name) {
        return forgeService.findItemByName(name);
    }

    @PatchMapping("/update")
    public ItemResponse updateItem(@RequestBody UpdateItem request) {
        return forgeService.updateItem(request);
    }

    @DeleteMapping
    public void deleteItem(@RequestParam String name) {
        forgeService.deleteItem(name);
    }

    @PatchMapping("/enhance")
    public EnhanceResponse enhance(@RequestBody EnhanceRequest request) {
        return forgeService.enhance(request);
    }
}
