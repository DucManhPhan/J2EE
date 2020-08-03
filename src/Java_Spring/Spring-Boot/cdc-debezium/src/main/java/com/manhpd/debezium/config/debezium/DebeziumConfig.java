package com.manhpd.debezium.config.debezium;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumConfig {

    @Bean
    public io.debezium.config.Configuration employeeConnector() {
        return io.debezium.config.Configuration.create()
                .with("connector.class", "io.debezium.connector.mysql.MySQLConnector")
    }

}
