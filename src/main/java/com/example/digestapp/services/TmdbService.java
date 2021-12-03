package com.example.digestapp.services;

public interface TmdbService {

    Long getFilmId(String filmName) throws IllegalArgumentException;
}
