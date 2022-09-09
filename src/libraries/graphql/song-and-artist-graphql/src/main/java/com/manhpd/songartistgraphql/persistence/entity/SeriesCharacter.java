package com.manhpd.songartistgraphql.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class SeriesCharacter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "occupation", nullable = false)
    private String occupation;

    @Column(name = "birthday")
    private LocalDate dateofbirth;

    @ManyToOne
    @JoinColumn(name = "id_series", nullable = false, updatable = false)
    private Series series;

    public SeriesCharacter(String name, String nickname, String occupation, LocalDate dateofbirth) {
        this.name = name;
        this.nickname = nickname;
        this.occupation = occupation;
        this.dateofbirth = dateofbirth;
    }

}
