package com.manhpd.onetomanyproblem.service;

import com.manhpd.onetomanyproblem.persistence.entity.FootballClub;
import com.manhpd.onetomanyproblem.persistence.repository.FootballClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballClubService {

    @Autowired
    private FootballClubRepository footballClubRepository;

    public List<FootballClub> getAllClubs() {
        return this.footballClubRepository.findAll();
    }

}
