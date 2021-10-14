package com.manhpd.basicspringplugin;

import org.springframework.stereotype.Component;

@Component
public class CsvWriter implements WriterPlugin {

    @Override
    public void write(String message) {
        System.out.println("Writing CSV: " + message);
    }

    @Override
    public boolean supports(String s) {
        return s.equalsIgnoreCase("csv");
    }

}
