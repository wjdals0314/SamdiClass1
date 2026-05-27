package com.example.samdiclass1.domain.print.service;

import com.example.samdiclass1.domain.print.entity.User;
import com.example.samdiclass1.domain.print.repository.UserRepository;
import com.example.samdiclass1.domain.print.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final로 적어둔 필드를 보고 그걸 넣는 생성자를 만들어줌
public class PrintService {
    private final UserRepository userRepository;

    public String printHelloWorld() {
        User user=User.builder()
                .username("홍정민")
                .password("qwer1234!")
                .build();

        userRepository.save(user);

        return "HelloWorld!";
        //
    }

    public void signup(CreateUserRequest request) {
        User user = User.builder()
                .username(request.username())
                .password(request.password())
                .build();
        userRepository.save(user);
    }

    // 유저 전체 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
