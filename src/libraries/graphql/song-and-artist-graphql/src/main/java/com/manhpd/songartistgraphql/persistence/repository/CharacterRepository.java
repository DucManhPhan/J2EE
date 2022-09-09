package com.manhpd.songartistgraphql.persistence.repository;

import com.manhpd.songartistgraphql.persistence.entity.SeriesCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<SeriesCharacter, Integer> {
}
