package com.example.digestapp.models.dto;

import com.example.digestapp.models.data.Digest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class DigestDto {

    private final String id;
    private final String filmName;
    private final String text;

    public DigestDto(Digest digest) {
        this.text = digest.getText();
        this.filmName = digest.getFilmName();
        this.id = digest.getId();
    }

    public static List<DigestDto> of(List<Digest> digests) {
        return digests.stream().map(DigestDto::new).collect(Collectors.toList());
    }
}
