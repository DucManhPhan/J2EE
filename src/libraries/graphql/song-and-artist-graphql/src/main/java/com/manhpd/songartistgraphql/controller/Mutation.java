package com.manhpd.songartistgraphql.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.manhpd.songartistgraphql.persistence.entity.Series;
import com.manhpd.songartistgraphql.persistence.entity.SeriesCharacter;
import com.manhpd.songartistgraphql.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private SeriesService service;

    public Series createSeries(String name, Integer nrOfSeasons) {
        return service.createSeries(name, nrOfSeasons);
    }

    public SeriesCharacter createCharacter(String name, String nickname, String occupation, String birthday, Integer seriesId) {
        LocalDate dayOfBirth = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);
        return service.createCharacter(name, nickname, occupation, dayOfBirth, seriesId);
    }
}

