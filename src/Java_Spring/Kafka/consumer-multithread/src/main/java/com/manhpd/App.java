package com.manhpd;

import com.manhpd.kafka.ConsumerThreads;
import com.manhpd.kafka.ProducerThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;


public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws URISyntaxException {
        logger.info("Running in the main function.");

        // run producer thread
        ProducerThread.start();

        // run consumer thread
        ConsumerThreads.start();
    }

}
