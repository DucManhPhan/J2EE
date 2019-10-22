package com.manhpd.read_file.multithreding;

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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(this.path))))) {
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                this.blockingQueue.put(currentLine);
                System.out.println(currentLine);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
