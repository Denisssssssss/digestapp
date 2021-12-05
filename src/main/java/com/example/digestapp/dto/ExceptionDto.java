package com.example.digestapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ExceptionDto implements Serializable {

    private final String message;
    private final String cause;
}
