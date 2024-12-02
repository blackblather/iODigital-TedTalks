package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void createIfNotExists(Author author) {
        if (authorRepository.findById(author.id).isEmpty()) {
            authorRepository.save(author);
        }
    }
}
