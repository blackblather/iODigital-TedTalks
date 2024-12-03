package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Talk;
import com.iodigital.tedtalks.entity.wrapper.TalksListWrapper;
import com.iodigital.tedtalks.repository.TalksRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TedTalksService {
    // Repositories
    private final TalksRepository talksRepository;

    TedTalksService(TalksRepository talksRepository) {
        this.talksRepository = talksRepository;
    }

    public boolean createIfNotExists(Talk talk) {
        if (talksRepository.findByUrl(talk.url).isEmpty()) {
            talksRepository.save(talk);
            return true;
        }
        return false;
    }

    public TalksListWrapper getAll() {
        List<Talk> talks = talksRepository.findAll();
        long totalTalks = talksRepository.count();

        return new TalksListWrapper(talks, totalTalks);
    }

    public Talk getByIdOrThrow(int id) {
        return talksRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void removeById(int id) {
        Talk talk = getByIdOrThrow(id);
        talksRepository.delete(talk);
    }

    public void updateById(Integer id, Talk updatedTalk) {
        // Get requested talk if it exists (404 otherwise)
        Talk persistedTalk = getByIdOrThrow(id);

        // Update fetched talk
        persistedTalk.title = updatedTalk.title;
        persistedTalk.views = updatedTalk.views;
        persistedTalk.likes = updatedTalk.likes;
        persistedTalk.url = updatedTalk.url;
        persistedTalk.year = updatedTalk.year;

        // Recalculate influence factor
        persistedTalk.calculateInfluenceFactor();

        // Update DB record
        talksRepository.save(persistedTalk);
    }

    public List<Talk> getMostInfluential() {
        // Fetch
        return talksRepository.mostInfluentialTalksPerYear();
    }
}
