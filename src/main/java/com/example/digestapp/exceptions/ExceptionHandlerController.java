package com.example.digestapp.exceptions;

import com.example.digestapp.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception e) {
       return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage(), e.getClass().getSimpleName()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDto> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ExceptionDto(e.getMessage(), e.getClass().getSimpleName()));
    }
}
