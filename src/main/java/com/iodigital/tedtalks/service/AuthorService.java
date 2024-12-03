package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.repository.AuthorRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void createIfNotExists(Author author) {
        if (authorRepository.findByName(author.name).isEmpty()) {
            authorRepository.save(author);
        }
    }

    public List<Author> getMostInfluential(int limit) {
        // Validations
        if (limit < 1 || limit > 50) {
            throw new IllegalArgumentException("Limit must be between 1 and 50 (inclusive).");
        }

        // Fetch
        return authorRepository.getTopByInfluence(PageRequest.of(0, limit));
    }
}
