package com.example.samdiclass1.domain.print.repository;

import com.example.samdiclass1.domain.print.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository가 Spring Data Jpa가 제공해주는 DB의 기본 기능 모음
// 저장, 데이터 찾기, 데이터 일부 찾기, 삭제하기
public interface UserRepository extends JpaRepository<User, Long> {

}
