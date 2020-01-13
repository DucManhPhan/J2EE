package com.manhpd.value_object;

public enum Priority {

    UNDETERMINED("undetermined"),

    MAX_PRIORITY("notification-max-priority"),

    MEDIUM_PRIORITY("notification-medium-priority"),

    MIN_PRIORITY("notification-min-priority");

    private String nameTopic;

    private Priority(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getNameTopic() {
        return this.nameTopic;
    }

}
