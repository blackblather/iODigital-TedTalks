package com.iodigital.tedtalks.repository;

import com.iodigital.tedtalks.entity.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Optional<Author> findByName(String name);

    @NativeQuery(
            value = """
                    select authors.name, sum(t.influence_factor) as author_influence
                    from authors
                    inner join public.talks t on authors.id = t.author_id
                    group by authors.name
                    order by author_influence desc
                    """,
            sqlResultSetMapping = "mostInfluentialAuthorsMapping"
    )
    List<Author> getTopByInfluence(Pageable page);
}
