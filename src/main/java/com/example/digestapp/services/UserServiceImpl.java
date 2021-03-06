package com.example.digestapp.services;

import com.example.digestapp.exceptions.EntityNotFoundException;
import com.example.digestapp.exceptions.IllegalUsernameException;
import com.example.digestapp.models.User;
import com.example.digestapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(String username, String password) {

        if (!userRepository.findByUsername(username).isPresent()) {
            return userRepository.save(new User(username, passwordEncoder.encode(password)));
        }
        throw new IllegalUsernameException("Username is already taken");
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
