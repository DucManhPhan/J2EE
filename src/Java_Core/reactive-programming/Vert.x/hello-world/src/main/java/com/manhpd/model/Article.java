package com.manhpd.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {

    private String id;

    private String content;

    private String author;

    private String datePublished;

    private int wordCount;

}
