package com.manhpd.debezium.config.debezium;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumConfig {

    @Bean
    public io.debezium.config.Configuration employeeConnector() {
        return io.debezium.config.Configuration.create()
                .with("connector.class", "io.debezium.connector.mysql.MySQLConnector")
                .with("offset.storage",  "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/path/cdc/offset/student-offset.dat")
                .with("offset.flush.interval.ms", 60000)
                .with("name", "student-postgres-connector")
                .with("database.server.name", studentDBHost + "-" + studentDBName)
                .with("database.hostname", studentDBHost)
                .with("database.port", studentDBPort)
                .with("database.user", studentDBUserName)
                .with("database.password", studentDBPassword)
                .with("database.dbname", studentDBName)
                .with("table.whitelist", STUDENT_TABLE_NAME).build();
    }

}
