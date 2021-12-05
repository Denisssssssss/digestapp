package com.example.digestapp.services;

import com.example.digestapp.dto.TokenDto;
import com.example.digestapp.exceptions.WrongCredentialsException;
import com.example.digestapp.models.User;
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
    public TokenDto signUp(String username, String password) {

        return new TokenDto(tokenProvider.generate(userService.save(username, password).getId()));
    }

    @Override
    public TokenDto signIn(String username, String password) {

        User user = userService.findByUsername(username);
        if (passwordEncoder.matches(password, user.getHashPassword())) {
            return new TokenDto(tokenProvider.generate(user.getId()));
        }
        throw new WrongCredentialsException();
    }
}
