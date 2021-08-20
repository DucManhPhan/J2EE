package com.manhpd.dto;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MonitorObject {

    private String currentTopic;

    public synchronized void setCurrentTopic(String currentTopic) {
        this.currentTopic = currentTopic;
    }

    public synchronized String getCurrentTopic() {
        return this.currentTopic;
    }
}
