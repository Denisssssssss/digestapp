package com.example.digestapp.services;

import com.example.digestapp.dto.TokenDto;

public interface AuthorizationService {

    TokenDto signUp(String username, String password);

    TokenDto signIn(String username, String password);
}
