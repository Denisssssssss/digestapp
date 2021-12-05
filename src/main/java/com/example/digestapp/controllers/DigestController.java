package com.example.digestapp.controllers;

import com.example.digestapp.dto.DigestDto;
import com.example.digestapp.security.UserDetailsImpl;
import com.example.digestapp.services.DigestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/digests")
public class DigestController {

    private final DigestService digestService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DigestDto> getAllDigests() {
        return digestService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DigestDto getDigest(@PathVariable("id") String id) {
        return digestService.findById(id);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DigestDto addDigest(
            @RequestBody DigestDto digestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return digestService.save(digestDto.getFilmName(), digestDto.getText(), userDetails.getUser());
    }

    @PutMapping(value = "/refactor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DigestDto refactorDigest(@PathVariable("id") String id,
                                                    @RequestBody DigestDto digestDto,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return digestService.refactor(id, digestDto.getText(), userDetails.getUser());
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteDigest(@PathVariable("id") String id, HttpServletResponse response,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        response.setStatus(
                digestService.delete(id, userDetails.getUser()) ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()
        );
    }
}
