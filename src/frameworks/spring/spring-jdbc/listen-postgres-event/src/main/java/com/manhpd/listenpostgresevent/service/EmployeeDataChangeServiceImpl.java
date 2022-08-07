package com.manhpd.listenpostgresevent.service;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.manhpd.listenpostgresevent.config.PostgresDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Service
public class EmployeeDataChangeServiceImpl implements EmployeeDataChangeService {

    private static final String CREATE_EMPLOYEE_DATA_EVENT = "watch_create_event_table_employee";

    private static final String UPDATE_EMPLOYEE_DATA_EVENT = "watch_update_event_table_employee";

    @Autowired
    private PostgresDataSourceConfig postgresDataSourceConfig;

    private PGConnection pgConnection;

    private PGNotificationListener listener;

    @Override
    @PostConstruct
    public void capture() {
        try {
            Class.forName("com.impossibl.postgres.jdbc.PGDriver");
            Connection conn = DriverManager.getConnection(postgresDataSourceConfig.getUrl(),
                                                          postgresDataSourceConfig.getUsername(),
                                                          postgresDataSourceConfig.getPassword());
            this.pgConnection = (PGConnection) conn;

            System.out.println("PG Connection: " + this.pgConnection);
            Statement listenStatement = this.pgConnection.createStatement();
            listenStatement.execute("LISTEN " + CREATE_EMPLOYEE_DATA_EVENT);
            listenStatement.close();

            this.listener = new PGNotificationListener() {
                @Override
                public void notification(int processId, String channelName, String payload) {
                    System.out.println("=== Payload: ===" + payload);
                }
            };
            this.pgConnection.addNotificationListener(listener);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
    }

    public void destroy() throws Throwable {
        Statement unlistenerStatement = this.pgConnection.createStatement();
        unlistenerStatement.execute("UNLISTEN " + CREATE_EMPLOYEE_DATA_EVENT);
        unlistenerStatement.close();
    }
}
