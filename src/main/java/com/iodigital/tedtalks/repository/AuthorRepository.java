package com.iodigital.tedtalks.repository;

import com.iodigital.tedtalks.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    @Override
    Optional<Author> findById(Integer integer);

}
