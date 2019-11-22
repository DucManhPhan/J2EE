package com.manhpd.using_blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Broker {

    public ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);

    public Boolean continueProducing = Boolean.TRUE;

    public void put(Integer data) throws InterruptedException {
        this.queue.put(data);
    }

    /**
     * Use poll() method instead of get() in the queue.
     * This is to ensure that the consumers will not keep waiting for ever and the waiting will time out after a few seconds.
     * This helps us in inter-communication and kill the consumers when all the data is processed.
     *
     * @return
     * @throws InterruptedException
     */
    public Integer get() throws InterruptedException {
        return this.queue.poll(1, TimeUnit.SECONDS);
    }

}
