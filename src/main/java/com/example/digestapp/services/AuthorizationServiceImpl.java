package com.example.digestapp.services;

import com.example.digestapp.models.data.User;
import com.example.digestapp.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String signUp(String username, String password) {

        return tokenProvider.generate(userService.save(username, password).getId());
    }

    @Override
    public String signIn(String username, String password) {

        User user = userService.findByUsername(username);
        if (passwordEncoder.matches(password, user.getHashPassword())) {
            return tokenProvider.generate(user.getId());
        } else throw new IllegalArgumentException("Wrong credentials");
    }
}
