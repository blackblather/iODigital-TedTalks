package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Talks;
import com.iodigital.tedtalks.entity.wrapper.TalksListWrapper;
import com.iodigital.tedtalks.repository.TalksRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TedTalksService {
    // Repositories
    private TalksRepository talksRepository;

    TedTalksService(TalksRepository talksRepository) {
        this.talksRepository = talksRepository;
    }

    public boolean createIfNotExists(Talks talk) {
        if (talksRepository.findByUrl(talk.url).isEmpty()) {
            talksRepository.save(talk);
            return true;
        }
        return false;
    }

    public TalksListWrapper getAll() {
        List<Talks> talks = talksRepository.findAll();
        long totalTalks = talksRepository.count();

        return new TalksListWrapper(talks, totalTalks);
    }

    public Talks getById(int id) {
        return talksRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void removeById(int id) {
        Talks talk = getById(id);
        talksRepository.delete(talk);
    }
}
