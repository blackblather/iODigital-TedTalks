package com.iodigital.tedtalks.repository;

import com.iodigital.tedtalks.entity.Talks;
import org.springframework.data.repository.CrudRepository;

public interface TalksRepository extends CrudRepository<Talks, Integer> {

}
