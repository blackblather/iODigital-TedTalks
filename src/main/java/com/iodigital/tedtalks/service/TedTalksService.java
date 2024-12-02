package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.repository.TalksRepository;
import org.springframework.stereotype.Service;

@Service
public class TedTalksService {
    // Repositories
    private TalksRepository talksRepository;

    TedTalksService(TalksRepository talksRepository) {
        this.talksRepository = talksRepository;
    }

    public void testSave() {
        Author author = new Author();
        author.name = "example author";

        talksRepository.save(author);
    }
}
