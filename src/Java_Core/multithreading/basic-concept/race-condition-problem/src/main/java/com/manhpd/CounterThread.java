package com.manhpd;

public class CounterThread implements Runnable {

    private String name;

    private PersonCounter counter;

    public CounterThread(String name, PersonCounter counter) {
        this.name = name;
        this.counter = counter;
    }

    public PersonCounter getCounter() {
        return this.counter;
    }

    @Override
    public void run() {
        final String orginName = Thread.currentThread().getName();
        Thread.currentThread().setName(this.name);

        this.counter.inc();

        System.out.println("In " + Thread.currentThread().getName() + ", value of this counter is: "
                + this.getCounter() + " after increment");
    }

}
