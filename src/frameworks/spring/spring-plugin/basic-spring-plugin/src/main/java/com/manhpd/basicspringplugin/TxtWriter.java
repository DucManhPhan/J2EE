package com.manhpd.basicspringplugin;

import org.springframework.stereotype.Component;

@Component
public class TxtWriter implements WriterPlugin {

    @Override
    public void write(String message) {
        System.out.println("Writing text: " + message);
    }

    @Override
    public boolean supports(String s) {
        return s.equalsIgnoreCase("txt");
    }
}
