package com.manhpd;


import com.manhpd.connection.ConnectionPool;
import com.manhpd.connection.ConnectionService;

import javax.swing.tree.FixedHeightLayoutCache;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Semaphore is a counter that allows a thread to get into a critical region.
 *
 * What the counter is counting are permits that allow access to the shared resource.
 * Thus, to access the resource, a thread must be granted a permit from the semaphore.
 *
 * If the value of the counter is greater than 0 then thread get the permit otherwise waits for the permit.
 * Once thread leaves the critical region increments the counter so that other thread can access the critical section.
 *
 * Most of the time we use semaphores to limit the number of concurrent threads accessing a specific resource.
 *
 */
public class Application {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ConnectionPool connectionPool = new ConnectionPool(5);

        ConnectionService connectionService = new ConnectionService(connectionPool);
        IntStream.range(0, 10).forEach(item -> {
            executorService.execute(connectionService);
        });
    }

}
