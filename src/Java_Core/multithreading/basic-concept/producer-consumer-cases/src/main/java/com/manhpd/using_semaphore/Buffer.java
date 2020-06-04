package com.manhpd.using_semaphore;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Buffer {

    private LinkedList<Integer> buffer = new LinkedList<>();

    private Semaphore semProducer = new Semaphore(1);

    private Semaphore semConsumer = new Semaphore(0);

    public void put(int value) {
        try {
            semProducer.acquire();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

        this.buffer.add(value);
        System.out.println("Producer push " + value + " to buffer in " + Thread.currentThread().getName());

        semConsumer.release();
    }

    public void get() {
        try {
            this.semConsumer.acquire();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

        System.out.println("Consumer is consuming element " + this.buffer.removeLast() + " in " + Thread.currentThread().getName());

        semProducer.release();
    }

}
