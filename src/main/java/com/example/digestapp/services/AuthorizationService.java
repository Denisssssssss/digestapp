package com.example.digestapp.services;

public interface AuthorizationService {

    String signUp(String username, String password);

    String signIn(String username, String password);
}
