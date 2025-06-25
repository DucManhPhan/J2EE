package com.manhpd.config_service.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.manhpd.config_service.persistence.repository")
public class DatabaseConfig {}
