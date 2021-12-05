package com.example.digestapp.services;

import com.example.digestapp.models.data.User;
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
                IllegalArgumentException.class, () ->
                digestService.save(invalidFilm, "text", new User())
        );
    }
}