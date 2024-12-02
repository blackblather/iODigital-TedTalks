package com.iodigital.tedtalks.repository;

import com.iodigital.tedtalks.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface TalksRepository extends CrudRepository<Author, Integer> {

}
