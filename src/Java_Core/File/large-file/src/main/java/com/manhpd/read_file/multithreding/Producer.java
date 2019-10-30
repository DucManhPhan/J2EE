package com.manhpd.read_file.multithreding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(Producer.class.getName());

    private BlockingQueue<String> blockingQueue;

    private String path;

    public Producer(BlockingQueue<String> blockingQueue, String path) {
        this.blockingQueue = blockingQueue;
        this.path = path;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(this.path))))) {
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                this.blockingQueue.put(currentLine);
                LOGGER.info("Data from producer: " + currentLine);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        System.out.printf("\nTook %.1f seconds to read all content of a file.\n", time / 1e3);
    }
}
