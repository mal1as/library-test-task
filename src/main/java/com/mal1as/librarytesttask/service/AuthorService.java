package com.mal1as.librarytesttask.service;

import com.mal1as.librarytesttask.mapper.AuthorMapper;
import com.mal1as.librarytesttask.model.dto.AuthorDTO;
import com.mal1as.librarytesttask.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDTO getMostPopularBetween(LocalDate from, LocalDate to) {
        return authorMapper.toAuthorDTO(authorRepository.findMostPopularBetween(from, to));
    }
}
