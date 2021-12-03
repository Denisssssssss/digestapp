package com.example.digestapp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tmdb-api", url = "${tmdb.api.url}")
public interface TmdbClient {

    @GetMapping("${tmdb.api.url.search-film}")
    String getFilms(
            @RequestParam(value = "max-rating", required = false) Double maxRating,
            @RequestParam(value = "min-rating", required = false) Double minRating,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "query") String query
    );
}
