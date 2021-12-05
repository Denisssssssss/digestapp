package com.example.digestapp.services;

import com.example.digestapp.dto.DigestDto;
import com.example.digestapp.exceptions.DigestRefactorException;
import com.example.digestapp.exceptions.EntityNotFoundException;
import com.example.digestapp.models.Digest;
import com.example.digestapp.models.User;
import com.example.digestapp.repositories.DigestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DigestServiceImpl implements DigestService {

    private final DigestRepository digestRepository;
    private final TmdbService tmdbService;

    @Override
    public DigestDto save(String filmName, String text, User user) {

        Long filmId = tmdbService.getFilmId(filmName);
        return new DigestDto(digestRepository.save(new Digest(filmName, text, user.getId(), filmId)));
    }

    @Override
    public List<DigestDto> findAll() {
        return DigestDto.of(digestRepository.findAll());
    }

    @Override
    public DigestDto findById(String id) {
        return new DigestDto(
                digestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Digest not found"))
        );
    }

    @Override
    public DigestDto refactor(String id, String newText, User user) {
        Digest digest = digestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Digest not found"));
        if (digest.getAuthorId().equals(user.getId())) {
            digest.setText(newText);
            return new DigestDto(digestRepository.save(digest));
        }
        throw new DigestRefactorException("Can't refactor someone else's digests");
    }

    @Override
    public boolean delete(String id, User user) {
        Digest digest = digestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Digest not found"));
        if (digest.getAuthorId().equals(user.getId())) {
            digestRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
