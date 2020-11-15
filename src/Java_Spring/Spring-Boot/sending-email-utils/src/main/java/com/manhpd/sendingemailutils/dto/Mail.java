package com.manhpd.sendingemailutils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String from;

    private String to;

    private String subject;

    private String content;

    // contains template variables for Thymeleaf
    private Map model;

}