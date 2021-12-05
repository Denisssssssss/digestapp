package com.example.digestapp.controllers;

import com.example.digestapp.models.dto.DigestDto;
import com.example.digestapp.models.dto.ExceptionDto;
import com.example.digestapp.security.UserDetailsImpl;
import com.example.digestapp.services.DigestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/digests")
public class DigestController {

    private final DigestService digestService;

    @GetMapping
    public ResponseEntity<List<DigestDto>> getAllDigests() {
        return ResponseEntity.ok(DigestDto.of(digestService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigestDto> getDigest(@PathVariable("id") String id) {
        return ResponseEntity.ok(new DigestDto(digestService.findById(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<DigestDto> addDigest(
            @RequestBody DigestDto digestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(
                new DigestDto(
                        digestService.save(digestDto.getFilmName(), digestDto.getText(), userDetails.getUser())
                )
        );
    }

    @PutMapping("/refactor/{id}")
    public ResponseEntity<DigestDto> refactorDigest(@PathVariable("id") String id,
                                                    @RequestBody DigestDto digestDto,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(new DigestDto(digestService.refactor(id, digestDto.getText(), userDetails.getUser())));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDigest(@PathVariable("id") String id, HttpServletResponse response,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        response.setStatus(
                digestService.delete(id, userDetails.getUser()) ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler({IllegalArgumentException.class, EntityNotFoundException.class, IllegalStateException.class})
    public ResponseEntity<ExceptionDto> handleBadRequest(Exception e) {
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage()));
    }
}
