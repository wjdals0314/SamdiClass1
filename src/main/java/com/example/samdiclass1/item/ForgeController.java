package com.example.samdiclass1.item;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forge")
@RequiredArgsConstructor
public class ForgeController {
    private final ForgeService forgeService;

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
}
