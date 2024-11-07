package com.mal1as.librarytesttask.controller;

import com.mal1as.librarytesttask.model.dto.AuthorDTO;
import com.mal1as.librarytesttask.model.dto.SuccessResponse;
import com.mal1as.librarytesttask.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/library/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/mostPopular")
    public ResponseEntity<SuccessResponse<AuthorDTO>> getMostPopularAuthorBetween(@RequestParam LocalDate from,
                                                                                  @RequestParam LocalDate to) {
        return ResponseEntity.ok(SuccessResponse.<AuthorDTO>builder()
                .content(authorService.getMostPopularBetween(from, to))
                .build());
    }
}
