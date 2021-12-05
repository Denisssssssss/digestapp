package com.example.digestapp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TmdbServiceImplTest {

    @Autowired
    private TmdbService tmdbService;

    @Test
    public void throwsExceptionOnIncorrectInput() {

        String invalidInput = "furwihuifniver";
        Assertions.assertThrows(IllegalArgumentException.class, () -> tmdbService.getFilmId(invalidInput));
    }

    @Test
    public void returnsLongOnCorrectInput() {

        String correctInput = "Harry Potter";
        Assertions.assertInstanceOf(Long.class, tmdbService.getFilmId(correctInput));
    }
}