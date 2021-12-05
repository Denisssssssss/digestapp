package com.example.digestapp.exceptions;

import org.springframework.http.HttpStatus;

public class WrongCredentialsException extends CustomException {

    public WrongCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "Wrong credentials");
    }
}
