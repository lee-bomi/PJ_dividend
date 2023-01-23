package com.zerobase.dividend.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ControllerAdvice
public class ErrorResponse {

    private int code;
    private String message;
}
