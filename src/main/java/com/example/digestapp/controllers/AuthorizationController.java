package com.example.digestapp.controllers;

import com.example.digestapp.models.dto.AuthorizationDto;
import com.example.digestapp.models.dto.ExceptionDto;
import com.example.digestapp.models.dto.TokenDto;
import com.example.digestapp.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/signUp")
    public ResponseEntity<TokenDto> signUp(@RequestBody AuthorizationDto authorizationDto) {
        return ResponseEntity.ok(
                new TokenDto(
                        authorizationService.signUp(authorizationDto.getUsername(), authorizationDto.getPassword())
                )
        );
    }

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody AuthorizationDto authorizationDto) {
        return ResponseEntity.ok(
                new TokenDto(
                        authorizationService.signIn(authorizationDto.getUsername(), authorizationDto.getPassword())
                )
        );
    }

    @ExceptionHandler({IllegalArgumentException.class, EntityNotFoundException.class})
    public ResponseEntity<ExceptionDto> handleBadRequest(Exception e) {
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage()));
    }
}
