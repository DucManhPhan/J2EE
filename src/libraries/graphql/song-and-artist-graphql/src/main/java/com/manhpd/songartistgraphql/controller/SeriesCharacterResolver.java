package com.manhpd.songartistgraphql.controller;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.manhpd.songartistgraphql.persistence.entity.Series;
import com.manhpd.songartistgraphql.persistence.entity.SeriesCharacter;
import com.manhpd.songartistgraphql.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeriesCharacterResolver implements GraphQLResolver<SeriesCharacter> {

    @Autowired
    private SeriesService seriesService;
    public Series getSeries(SeriesCharacter character) {
        return seriesService.getSeries(character.getSeries().getId());
    }

}
