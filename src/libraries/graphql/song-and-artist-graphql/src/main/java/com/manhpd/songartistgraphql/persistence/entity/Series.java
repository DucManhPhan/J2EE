package com.manhpd.songartistgraphql.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Series implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "seasons")
    private Integer nrOfSeasons;

    public Series(String name, Integer nrOfSeasons) {
        this.name = name;
        this.nrOfSeasons = nrOfSeasons;
    }

}
