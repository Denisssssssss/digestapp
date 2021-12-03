package com.example.digestapp.services;

import com.example.digestapp.feign.TmdbClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TmdbServiceImpl implements TmdbService {

    private final TmdbClient tmdbClient;

    @Override
    public Long getFilmId(String filmName) {

        try {
            return new JSONObject(tmdbClient.getFilms(null, null, 1, filmName))
                    .getJSONArray("results")
                    .getJSONObject(0)
                    .getLong("id");
        } catch (JSONException e) {
            throw new IllegalArgumentException("Film not found");
        }
    }
}
