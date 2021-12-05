package com.example.digestapp.services;

import com.example.digestapp.models.data.User;

public interface UserService {

    User save(String username, String password);

    User findById(Long id);

    User findByUsername(String username);
}
