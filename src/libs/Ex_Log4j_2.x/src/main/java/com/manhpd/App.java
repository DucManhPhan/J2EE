package com.manhpd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) throws InterruptedException {
        for(int i = 0;; i++) {
            System.out.println("Logging.\n");
            logger.info("This is the " + i + " time I say 'Hello World'.");
            Thread.sleep(100);
        }
    }
}
