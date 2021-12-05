package com.example.digestapp.exceptions;

import org.springframework.http.HttpStatus;

public class DigestRefactorException extends CustomException {
    public DigestRefactorException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
