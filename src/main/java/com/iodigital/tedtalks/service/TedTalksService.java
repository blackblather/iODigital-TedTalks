package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Talks;
import com.iodigital.tedtalks.entity.wrapper.TalksListWrapper;
import com.iodigital.tedtalks.repository.TalksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
