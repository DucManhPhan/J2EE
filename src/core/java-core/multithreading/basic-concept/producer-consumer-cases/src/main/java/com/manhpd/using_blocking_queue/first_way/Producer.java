package com.manhpd.using_blocking_queue.first_way;

public class Producer implements Runnable {

    private Broker broker;

    public Producer(Broker broker) {
        this.broker = broker;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 5; ++i) {
                System.out.println("Producer thread: " + i);
                Thread.sleep(100);
                broker.put(i);
            }

            this.broker.continueProducing = Boolean.FALSE;
            System.out.println("Producer finished its job --> terminating.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
