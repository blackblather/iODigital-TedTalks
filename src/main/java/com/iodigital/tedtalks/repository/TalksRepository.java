package com.iodigital.tedtalks.repository;

import com.iodigital.tedtalks.entity.Talk;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TalksRepository extends CrudRepository<Talk, Integer> {
    Optional<Talk> findByUrl(String url);

    @Override
    @Query("select t from Talk t " +
            "join fetch t.author")
    List<Talk> findAll();

    @Override
    @Query("select t from Talk t " +
            "join fetch t.author " +
            "where t.id = :id")
    Optional<Talk> findById(@Param("id") Integer id);

    @NativeQuery(
            value = """
                    with maxPerYear as (
                        select max(influence_factor) as max_influence, year
                        from talks
                        group by year
                    )
                    select a.id as a_id,
                           a.name,
                           t.id as t_id,
                           t.author_id,
                           t.title,
                           t.views,
                           t.likes,
                           t.url,
                           t.influence_factor,
                           t.year
                    from talks as t
                    inner join authors a on a.id = t.author_id
                    inner join maxPerYear m on m.max_influence = t.influence_factor and m.year = t.year
                    order by t.year desc
                    """,
            sqlResultSetMapping = "mostInfluentialTalksPerYearMapping"
    )
    List<Talk> mostInfluentialTalksPerYear();
}
