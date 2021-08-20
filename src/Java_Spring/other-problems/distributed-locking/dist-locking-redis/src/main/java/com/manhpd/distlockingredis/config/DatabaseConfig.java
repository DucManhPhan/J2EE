package com.manhpd.distlockingredis.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(value = "app.datasource.default.enable", havingValue = "true", matchIfMissing = true)
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.manhpd.distlockingredis"})
public class DatabaseConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.default")
    public DataSourceProperties defaultDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "app.datasource.default.configuration")
    public DataSource dataSource() {
        return defaultDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.manhpd.distlockingredis.persistence.entity")
                .persistenceUnit("main")
                .build();
    }
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager mainTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}