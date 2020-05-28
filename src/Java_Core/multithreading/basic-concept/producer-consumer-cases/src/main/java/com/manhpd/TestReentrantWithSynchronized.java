package com.manhpd;

import com.manhpd.using_blocking_queue.HighThroughputMain;


public class TestReentrantWithSynchronized {

    public static void main(String[] args) throws InterruptedException {
//        HighThroughputMain.start();

        Testcase testcase = new Testcase();

        Runnable task = () -> {
            try {
                testcase.testcase1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        thread.join();

        System.out.println("The end");
    }
}

class Testcase {

    private Object lock;

    public Testcase() {
        // nothing to do
    }

    public Testcase(Object lock) {
        this.lock = lock;
    }

    public synchronized void testcase1() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Testcase1 is implementing.");

        testcase2();
    }

    public synchronized void testcase2() throws InterruptedException {
//        Thread.sleep(3000);
        System.out.println("Testcase2 is implementing.");
    }

}