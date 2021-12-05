package com.example.digestapp.services;

import com.example.digestapp.dto.DigestDto;
import com.example.digestapp.models.User;

import java.util.List;

public interface DigestService {

    DigestDto save(String filmName, String text, User user);

    List<DigestDto> findAll();

    DigestDto findById(String id);

    DigestDto refactor(String id, String newText, User user);

    boolean delete(String id, User user);
}
