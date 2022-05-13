package com.manhpd.onetomanyproblem;

import com.manhpd.onetomanyproblem.persistence.entity.FootballClub;
import com.manhpd.onetomanyproblem.service.FootballClubService;
import com.manhpd.onetomanyproblem.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OneToManyProblemApplication implements CommandLineRunner {

    @Autowired
    private FootballClubService footballClubService;

    @Autowired
    private PlayerService playerService;

    public static void main(String[] args) {
        SpringApplication.run(OneToManyProblemApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        List<FootballClub> footballClubs = this.footballClubService.getAllClubs();
//        System.out.println(footballClubs);
    }
}
