package com.manhpd.connection;

public class ConnectionService implements Runnable {

    private ConnectionPool connectionPool;

    public ConnectionService(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void run() {
        this.connectionPool.getConnectionFromPool();
    }
}
