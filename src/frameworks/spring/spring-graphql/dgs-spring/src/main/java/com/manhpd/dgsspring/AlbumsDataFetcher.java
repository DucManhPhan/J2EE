package com.manhpd.dgsspring;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class AlbumsDataFetcher {

    private final List<Album> albums = Arrays.asList(
        new Album("Rumours", "Fleetwood Mac", 20),
        new Album("What's Going On", "Marvin Gaye", 10),
        new Album("Pet Sounds", "The Beach Boys", 12)
    );

    @DgsQuery
    public List<Album> albums(@InputArgument String titleFilter) {
        if (StringUtils.isEmpty(titleFilter)) {
            return this.albums;
        }

        return albums.stream()
                .filter(album -> album.getTitle().contains(titleFilter))
                .collect(Collectors.toList());
    }

}
