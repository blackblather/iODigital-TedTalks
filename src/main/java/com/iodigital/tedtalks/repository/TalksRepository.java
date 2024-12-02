package com.iodigital.tedtalks.repository;

import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.entity.Talks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TalksRepository extends CrudRepository<Talks, Integer> {
    Optional<Talks> findByUrl(String url);

    @Override
    @Query("select t from Talks t join fetch t.author")
    List<Talks> findAll();
}
