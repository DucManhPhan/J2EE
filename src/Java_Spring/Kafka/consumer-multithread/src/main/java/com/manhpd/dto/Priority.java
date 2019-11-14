package com.manhpd.dto;

public enum Priority {

    MIN_PRIORITY("MIN_PRIORITY"),

    MEDIUM_PRIORITY("MEDIUM_PRIORITY"),

    MAX_PRIORITY("MAX_PRIORITY");

    private String name;

    private Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
