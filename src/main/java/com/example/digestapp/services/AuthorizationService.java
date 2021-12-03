package com.example.digestapp.services;

import javax.persistence.EntityNotFoundException;

public interface AuthorizationService {

    String signUp(String username, String password) throws IllegalArgumentException;

    String signIn(String username, String password) throws EntityNotFoundException, IllegalArgumentException;
}
