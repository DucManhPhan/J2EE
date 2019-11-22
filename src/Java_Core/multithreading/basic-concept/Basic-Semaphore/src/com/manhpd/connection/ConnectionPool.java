package com.manhpd.connection;

import java.util.concurrent.Semaphore;


/**
 * tryAcquire() method - Return true if a permit is available immediately and acquire it otherwise return false,
 * acquire()- Acquires a permit and blocking until one is available.
 * release() – Release a permit
 * availablePermits() – Return number of current permits available
 */
public class ConnectionPool {

    private Semaphore connectionSemaphore;

    public ConnectionPool(int poolSize) {
        this.connectionSemaphore = new Semaphore(poolSize);
    }

    public void getConnectionFromPool() {
        if (connectionSemaphore.availablePermits() > 0) {
            connectionSemaphore.tryAcquire();
            System.out.println("Get the connection.");
        } else {
            System.out.println("Max connection limit.");
        }
    }

}
