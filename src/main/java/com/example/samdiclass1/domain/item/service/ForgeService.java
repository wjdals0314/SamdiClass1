package com.example.samdiclass1.domain.item.service;


import com.example.samdiclass1.domain.item.dto.request.CreateItemRequest;
import com.example.samdiclass1.domain.item.dto.request.EnhanceRequest;
import com.example.samdiclass1.domain.item.dto.request.EnhanceResponse;
import com.example.samdiclass1.domain.item.dto.response.ItemResponse;
import com.example.samdiclass1.domain.item.dto.request.UpdateItem;
import com.example.samdiclass1.domain.item.entity.Item;
import com.example.samdiclass1.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgeService {
    public final ItemRepository itemRepository;

    public void creamItem(CreateItemRequest request) {
        Item foundItem = itemRepository.findByName(request.name()).orElse(null);
        // 만약에 DB에 이미 있다면
        if (foundItem != null) {
            throw new IllegalArgumentException(request.name() + " 이름의 아이템이 이미 있습니다.");
        }

        // 공격력이 1보다 작을 때
        if (request.attackPower() <= 0) {
            throw new IllegalArgumentException("공격력은 1 이상이어야 합니다.");
        }

        // 내구도가 1보다 작을 때
        if (request.durability() <= 0) {
            throw new IllegalArgumentException("내구도는 1 이상이어야 합니다.");
        }

        Item item = Item.builder()
                .name(request.name())
                .type(request.type())
                .attackPower(request.attackPower())
                .enhancedLevel(0)
                .build();
        itemRepository.save(item);
    }

    // 이름으로 조회
    public ItemResponse findItemByName(String name) {
        Item item = itemRepository.findByName(name).orElse(null);
        // 만약 DB에 있다면
        if (item == null) {
            throw new IllegalArgumentException(name + "는 없는 아이템입니다.");
        }
        return ItemResponse.of(item);
    }

    // 강화 로직 넣기 전 일반 수정 메서드
    public ItemResponse updateItem(UpdateItem request) {
        Item item = itemRepository.findByName(request.name()).orElse(null);

        // 만약 DB에 없다면
        if (item == null) {
            throw new IllegalArgumentException(request.name() + "는 없는 아이템입니다.");
        }

        // 공격력이 1보다 낮을 때
        if (request.attackPower() <= 0) {
            throw new IllegalArgumentException("공격력은 1 이상이어야 합니다.");
        }
        item.setAttackPower(request.attackPower());
        item.setDurability(request.durability());

        itemRepository.save(item);

        return ItemResponse.of(item);
    }

    // 아이템 삭제
    public void deleteItem(String name) {
        Item item = itemRepository.findByName(name).orElse(null);

        // 만약 DB에 없다면
        if (item == null) {
            throw new IllegalArgumentException(name + "는 없는 아이템입니다.");
        }

        itemRepository.delete(item);
    }

    //아이템 강화
    public EnhanceResponse enhance(EnhanceRequest request) {
        Item item = itemRepository.findByName(request.name()).orElseThrow(
                () -> new IllegalArgumentException("에러 내용")
        );
        if (item.getEnhancedLevel() >= 10) {
            throw new IllegalArgumentException(item.getName() + "은(는) 이미 최대 강화 단계이므로 더 이상 강화할 수 없습니다.");
        }

        int successRate = getSuccessRate(item.getEnhancedLevel());
        int randomNumber = (int) (Math.random() * 100) + 1;

        if (randomNumber <= successRate) {
            item.setEnhancedLevel(item.getEnhancedLevel() + 1);
            item.setAttackPower(item.getAttackPower() + 2);


            itemRepository.save(item);

            return new EnhanceResponse(
                    item.getName(),
                    true,
                    item.getAttackPower(),
                    item.getDurability(),
                    item.getEnhancedLevel(),
                    "강화 성공!"

            );
        }
        item.setDurability(item.getDurability() - 1);

        if (item.getDurability() <= 0) {
            itemRepository.delete(item);

            return new EnhanceResponse(
                    item.getName(),
                    false,
                    item.getAttackPower(),
                    0,
                    item.getEnhancedLevel(),
                    "내구도0이 되어 아이템이 파괴되었습니다."
            );
        }
        itemRepository.save(item);
        return new EnhanceResponse(
                item.getName(),
                false,
                item.getAttackPower(),
                item.getDurability(),
                item.getEnhancedLevel(),
                "강화 실패"
        );
    }


    private int getSuccessRate(int enhanceLevel) {
        if (enhanceLevel <= 2) {
            return 80;
        }
        if (enhanceLevel <= 5) {
            return 60;
        }
        return 40;
    }
}
