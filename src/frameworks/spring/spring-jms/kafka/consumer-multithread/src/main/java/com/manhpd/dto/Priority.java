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

    public Priority next() {
        if (ordinal() == values().length - 1) {
            return values()[0];
        }

        return values()[ordinal() + 1];
    }

    public Priority previous() {
        if (ordinal() == 0) {
            return values()[values().length - 2];
        }

        return values()[ordinal() - 1];
    }

}

//class MainEnum {
//
//    public static void main(String[] args) {
//        Priority priority = Priority.MAX_PRIORITY;
//        Priority nextPriority = priority.next();
//        System.out.println(nextPriority.getName());
//    }
//
//}
