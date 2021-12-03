package com.example.digestapp.services;

import com.example.digestapp.models.data.Digest;
import com.example.digestapp.models.data.User;
import com.example.digestapp.repositories.DigestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DigestServiceImpl implements DigestService {

    private final DigestRepository digestRepository;
    private final TmdbService tmdbService;

    @Override
    public Digest save(String filmName, String text, User user) {

        Long filmId = tmdbService.getFilmId(filmName);
        return digestRepository.save(new Digest(filmName, text, user.getId(), filmId));
    }

    @Override
    public List<Digest> findAll() {
        return digestRepository.findAll();
    }

    @Override
    public Digest findById(String id) {
        return digestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Digest not found"));
    }

    @Override
    public Digest refactor(String id, String newText) {

        Digest digest = findById(id);
        digest.setText(newText);
        return digestRepository.save(digest);
    }

    @Override
    public boolean delete(String id, User user) {
        if (findById(id).getAuthorId().equals(user.getId())) {
            digestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
