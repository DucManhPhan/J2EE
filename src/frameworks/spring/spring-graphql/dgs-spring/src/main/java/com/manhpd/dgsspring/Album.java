package com.manhpd.dgsspring;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Album {
    private final String title;
    private final String artist;
    private final Integer recordNo;
}
