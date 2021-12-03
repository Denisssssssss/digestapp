package com.example.digestapp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizationDto {

    private final String username;
    private final String password;
}
