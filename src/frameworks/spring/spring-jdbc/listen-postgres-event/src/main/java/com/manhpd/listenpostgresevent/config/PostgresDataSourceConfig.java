package com.manhpd.listenpostgresevent.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "database.postgres")
public class PostgresDataSourceConfig {

    @Value("${database.postgres.url}")
    private String url;

    @Value("${database.postgres.username}")
    private String username;

    @Value("${database.postgres.password}")
    private String password;

}
