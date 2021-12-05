package com.example.digestapp.controllers;

import com.example.digestapp.dto.AuthorizationDto;
import com.example.digestapp.dto.TokenDto;
import com.example.digestapp.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping(value = "/signUp", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TokenDto signUp(@RequestBody AuthorizationDto authorizationDto) {

        return authorizationService.signUp(authorizationDto.getUsername(), authorizationDto.getPassword());
    }

    @PostMapping(value = "/signIn", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TokenDto signIn(@RequestBody AuthorizationDto authorizationDto) {
        return authorizationService.signIn(authorizationDto.getUsername(), authorizationDto.getPassword());
    }
}
