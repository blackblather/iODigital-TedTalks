package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.entity.Talks;
import com.iodigital.tedtalks.repository.TalksRepository;
import org.springframework.stereotype.Service;

@Service
public class TedTalksService {
    // Repositories
    private TalksRepository talksRepository;

    TedTalksService(TalksRepository talksRepository) {
        this.talksRepository = talksRepository;
    }

    public boolean save(Talks talk, Author author) {
        try {
            talksRepository.save(talk);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
