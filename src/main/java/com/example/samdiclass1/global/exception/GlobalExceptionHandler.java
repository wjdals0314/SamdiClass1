package com.example.samdiclass1.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 여러 컨트롤러에서 발생한 예외를 한 곳에서 처리
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class) // 이 예외가 발생했을 때 아래 메서드 실행
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // NOT_FOUND, CONFLICT
                .body(ex.getMessage());
    }

}

