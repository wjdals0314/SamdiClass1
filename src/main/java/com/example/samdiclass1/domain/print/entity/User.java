package com.example.samdiclass1.domain.print.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;
}
