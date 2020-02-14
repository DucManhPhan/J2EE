package com.manhpd.standard;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {

    private AtomicInteger firstJobDone = new AtomicInteger(0);

    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo() {
        // nothing to do
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        // mark the first job as done, by increasing its count
        this.firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (this.firstJobDone.get() != 1) {
            // waiting for the first job to be done
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        // mark the first job as done, by increasing its count
        this.secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (this.secondJobDone.get() != 1) {
            // waiting for the first job to be done
        }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}
