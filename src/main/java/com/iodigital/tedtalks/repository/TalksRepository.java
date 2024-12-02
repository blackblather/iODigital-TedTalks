package com.iodigital.tedtalks.repository;

import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.entity.Talks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TalksRepository extends CrudRepository<Talks, Integer> {
    Optional<Talks> findByUrl(String url);

    @Override
    @Query("select t from Talks t join fetch t.author")
    List<Talks> findAll();

    @Override
    @Query("select t from Talks t join fetch t.author where t.id = :id")
    Optional<Talks> findById(@Param("id") Integer id);
}
