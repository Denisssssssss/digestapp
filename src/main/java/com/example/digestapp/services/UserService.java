package com.example.digestapp.services;

import com.example.digestapp.models.data.User;

import javax.persistence.EntityNotFoundException;

public interface UserService {

    User save(String username, String password) throws IllegalArgumentException;

    User findById(Long id) throws EntityNotFoundException;

    User findByUsername(String username) throws EntityNotFoundException;
}
