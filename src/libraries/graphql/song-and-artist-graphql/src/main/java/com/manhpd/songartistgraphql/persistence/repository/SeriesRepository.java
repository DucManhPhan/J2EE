package com.manhpd.songartistgraphql.persistence.repository;

import com.manhpd.songartistgraphql.persistence.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {
}
