package com.manhpd.songartistgraphql.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.manhpd.songartistgraphql.persistence.entity.Series;
import com.manhpd.songartistgraphql.persistence.entity.SeriesCharacter;
import com.manhpd.songartistgraphql.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private SeriesService service;
    public List<SeriesCharacter> characters() {
        log.info("==== CHARACTERS QUERY ====");
        return service.getCharacters();
    }

    public SeriesCharacter character(Integer id) {
        log.info("==== CHARACTER BY ID QUERY ====");
        return service.getCharacter(id);
    }

    public List<Series> allSeries() {
        log.info("==== SERIES QUERY ====");
        return service.getSeries();
    }

    public Series series(Integer id) {
        log.info("==== SERIES BY ID QUERY ====");
        return service.getSeries(id);
    }
}

