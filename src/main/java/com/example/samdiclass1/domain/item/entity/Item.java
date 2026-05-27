package com.example.samdiclass1.domain.item.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "itmes")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // 아이템 이름

    private String type; // 아이템 유형(칼, 도끼 등...)

    @Setter // 수정이 될 수 있음
    private int attackPower; // 공격력

    @Setter // 수정이 될 수 있음
    private int enchancedLevel; // 강화 레벨
    @Setter
    private int durability; // 내구도
}
