package com.manhpd.onetomanyproblem.persistence.repository;

import com.manhpd.onetomanyproblem.persistence.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
