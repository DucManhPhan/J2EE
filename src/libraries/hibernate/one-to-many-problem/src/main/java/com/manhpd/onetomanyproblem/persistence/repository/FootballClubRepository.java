package com.manhpd.onetomanyproblem.persistence.repository;

import com.manhpd.onetomanyproblem.persistence.entity.FootballClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballClubRepository extends JpaRepository<FootballClub, Integer> {
}
