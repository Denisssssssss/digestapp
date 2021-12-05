package com.example.digestapp.services;

import com.example.digestapp.exceptions.EntityNotFoundException;
import com.example.digestapp.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DigestServiceImplTest {

    @Autowired
    private DigestService digestService;

    @Test
    public void throwsExceptionOnNonExistentFilm() {

        String invalidFilm = "fnwoinwre";
        Assertions.assertThrows(
                EntityNotFoundException.class, () ->
                digestService.save(invalidFilm, "text", new User())
        );
    }
}