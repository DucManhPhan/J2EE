package com.manhpd;

public class PersonCounter {

    private Integer counter = 0;

    public PersonCounter() {
        this(0);
    }

    public PersonCounter(Integer counter) {
        this.counter = counter;
    }

    public synchronized void inc() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ++counter;
    }

    public void dec() {
        --counter;
    }

    public int getValue() {
        return this.counter;
    }

}
