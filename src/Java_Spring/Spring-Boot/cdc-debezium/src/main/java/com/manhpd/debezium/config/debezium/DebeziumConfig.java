package com.manhpd.debezium.config.debezium;

import io.debezium.connector.mysql.MySqlConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumConfig {

    @Value("${debezium.datasource.host-name}")
    private String employeeDbHost;

    @Value("${debezium.datasource.database-name}")
    private String employeeDbname;

    @Value("${debezium.datasource.port}")
    private String port;

    @Value("${debezium.datasource.username}")
    private String username;

    @Value("${debezium.datasource.password}")
    private String password;

    private String EMPLOYEE_DB_NAME = "employee_sample.employee";

    @Bean
    public io.debezium.config.Configuration employeeConnector() {
         io.debezium.config.Configuration config =  io.debezium.config.Configuration.create()
                .with("connector.class", MySqlConnector.class)  //"io.debezium.connector.mysql.MySQLConnector")
                .with("offset.storage",  "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/home/manhpd/manhpd5/github/J2EE/src/Java_Spring/Spring-Boot/cdc-debezium/employee-offset.dat")
                .with("offset.flush.interval.ms", 60000)
                .with("name", "employee-mysql-connector")
                .with("debezium.source.database.history", "io.debezium.relational.history.MemoryDatabaseHistory")
//                .with("debezium.source.database.history.file.filename", "/home/manhpd/manhpd5/github/J2EE/src/Java_Spring/Spring-Boot/cdc-debezium/db-history.dat")
                .with("database.server.name", this.employeeDbHost + "-" + this.employeeDbname)
                .with("database.hostname", this.employeeDbHost)
                .with("database.port", this.port)
                .with("database.user", this.username)
                .with("database.password", this.password)
                .with("database.dbname", this.employeeDbname)
                .with("table.whitelist", EMPLOYEE_DB_NAME)
                .build();
         return config;
    }

}
