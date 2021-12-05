package com.example.digestapp.exceptions;

import org.springframework.http.HttpStatus;

public class IllegalUsernameException extends CustomException {
    public IllegalUsernameException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
