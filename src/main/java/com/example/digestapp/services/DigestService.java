package com.example.digestapp.services;

import com.example.digestapp.models.data.Digest;
import com.example.digestapp.models.data.User;

import java.util.List;

public interface DigestService {

    Digest save(String filmName, String text, User user);

    List<Digest> findAll();

    Digest findById(String id);

    Digest refactor(String id, String newText, User user);

    boolean delete(String id, User user);
}
