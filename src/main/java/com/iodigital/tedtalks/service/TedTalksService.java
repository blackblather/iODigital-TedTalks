package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Talks;
import com.iodigital.tedtalks.entity.wrapper.TalksListWrapper;
import com.iodigital.tedtalks.repository.TalksRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TedTalksService {
    // Repositories
    private TalksRepository talksRepository;

    TedTalksService(TalksRepository talksRepository, AuthorService authorService) {
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

    public Talks getByIdOrThrow(int id) {
        return talksRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void removeById(int id) {
        Talks talk = getByIdOrThrow(id);
        talksRepository.delete(talk);
    }

    public void updateById(Integer id, Talks updatedTalk) {
        // Get requested talk if it exists (404 otherwise)
        Talks persistedTalk = getByIdOrThrow(id);

        // Update fetched talk (for demo purposes, the author is also updated. In the real world it probably would make more sense to have a separate endpoint to update the author)
        persistedTalk.author.name = updatedTalk.author.name;
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
}
