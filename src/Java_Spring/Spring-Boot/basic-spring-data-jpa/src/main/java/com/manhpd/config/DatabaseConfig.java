package com.manhpd.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.manhpd.persistence.repository")
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    private final String PROPERTY_DRIVER = "driver";
    private final String PROPERTY_URL = "url";
    private final String PROPERTY_USERNAME = "usermysql";
    private final String PROPERTY_PASSWORD = "password";
    private final String PROPERTY_DIALECT = "hibernate.dialect";
    private final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
    private final String PROPERTY_DDL_AUTO = "hibernate.ddl-auto";
    private final String PROPERTY_VALIDATION_QUERY = "hibernate.validationQuery";
    private final String PROPERTY_TEST_ON_BORROW = "hibernate.testOnBorrow";

    @Autowired
    private Environment env;

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lfb.setPackagesToScan("com.manhpd.persistence.entity");
        lfb.setJpaProperties(hibernateProps());

        return lfb;
    }

    @Bean
    DataSource dataSource() {
        // 1st way - use DriverManagerDataSource
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setUrl(this.env.getProperty(PROPERTY_URL));
//        ds.setUsername(this.env.getProperty(PROPERTY_USERNAME));   //this.getRealValue(this.env.getProperty(PROPERTY_USERNAME)));
//        ds.setPassword(this.env.getProperty(PROPERTY_PASSWORD));   //this.getRealValue(this.env.getProperty(PROPERTY_PASSWORD)));
//        ds.setDriverClassName(this.env.getProperty(PROPERTY_DRIVER));
//        return ds;

        // 2nd way - use Connection Pool of Tomcat
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(this.env.getProperty(PROPERTY_URL));
        poolProperties.setUsername(this.env.getProperty(PROPERTY_USERNAME));
        poolProperties.setPassword(this.env.getProperty(PROPERTY_PASSWORD));
        poolProperties.setDriverClassName(this.env.getProperty(PROPERTY_DRIVER));
        poolProperties.setTestOnBorrow(Boolean.parseBoolean(this.env.getProperty(PROPERTY_TEST_ON_BORROW)));
        poolProperties.setValidationQuery(this.env.getProperty(PROPERTY_VALIDATION_QUERY));
//        poolProperties.setValidationInterval(0);
        DataSource ds = new DataSource(poolProperties);
        return ds;
    }

    Properties hibernateProps() {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_DIALECT, this.env.getProperty(PROPERTY_DIALECT));
        properties.setProperty(PROPERTY_SHOW_SQL, this.env.getProperty(PROPERTY_SHOW_SQL));
        return properties;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

}
