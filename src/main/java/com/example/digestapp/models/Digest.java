package com.example.digestapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(value = "digests")
@Getter
@Setter
@NoArgsConstructor
public class Digest {

    @Id
    private String id;

    private String filmName;

    private String text;

    private Long authorId;

    private Long filmId;

    public Digest(String filmName, String text, Long authorId, Long filmId) {
        this.filmName = filmName;
        this.text = text;
        this.authorId = authorId;
        this.filmId = filmId;
    }
}
