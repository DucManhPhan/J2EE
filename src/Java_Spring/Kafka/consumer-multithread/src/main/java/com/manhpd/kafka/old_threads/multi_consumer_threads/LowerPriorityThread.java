package com.manhpd.kafka.old_threads.multi_consumer_threads;

import com.manhpd.dto.Priority;

public class LowerPriorityThread implements Runnable {

    private Priority priority;

    private Object monitor;

    public LowerPriorityThread(Object monitor, Priority priority) {
        this.priority = priority;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        
    }
}
