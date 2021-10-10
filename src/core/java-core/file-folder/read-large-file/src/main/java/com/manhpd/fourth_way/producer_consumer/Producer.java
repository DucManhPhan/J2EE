package com.manhpd.fourth_way.producer_consumer;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {


    private BlockingQueue<String> blockingQueue;

    private String path;

    public Producer(BlockingQueue<String> blockingQueue, String path) {
        this.blockingQueue = blockingQueue;
        this.path = path;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(this.path)))) {
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                this.blockingQueue.put(currentLine);
                System.out.println("Data from producer: " + currentLine);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        long duration = System.currentTimeMillis() - start;
        System.out.printf("Took %.1f seconds to read all content of a file.", duration / 1e3);
    }
}
