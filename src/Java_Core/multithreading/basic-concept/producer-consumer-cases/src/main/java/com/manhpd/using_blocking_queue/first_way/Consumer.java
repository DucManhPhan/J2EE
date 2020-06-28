package com.manhpd.using_blocking_queue.first_way;

public class Consumer implements Runnable {

    private String name;

    private Broker broker;

    public Consumer(String name, Broker broker) {
        this.name = name;
        this.broker = broker;
    }

    @Override
    public void run() {
        try {
            Integer data = broker.get();
            while (this.broker.continueProducing || data != null) {
                Thread.sleep(1000);
                System.out.println("Consumer " + this.name + " processed data from broker: " + data);
                data = broker.get();
            }

            System.out.println("Consumer " + this.name + " terminating its job --> terminating.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
