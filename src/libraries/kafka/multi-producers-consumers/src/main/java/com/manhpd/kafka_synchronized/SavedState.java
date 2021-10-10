package com.manhpd.kafka_synchronized;

public class SavedState {

    // Run max priority thread first
//    public volatile int status = 1;

    public volatile boolean isMaxPriorityThreadRunnable = true;

    public volatile boolean isMediumPriorityThreadRunnable = false;

    public boolean isNotMinPriorityThreadRunnable() {
        return this.isMaxPriorityThreadRunnable || this.isMediumPriorityThreadRunnable;
    }

    public boolean isNotMediumPriorityThreadRunnable() {
        return this.isMaxPriorityThreadRunnable;
    }

    @Override
    public String toString() {
        return "isMaxPriorityThreadRunnable: " + this.isMaxPriorityThreadRunnable + " - isMediumPriorityThreadRunnable: " + this.isMediumPriorityThreadRunnable;
    }

}
