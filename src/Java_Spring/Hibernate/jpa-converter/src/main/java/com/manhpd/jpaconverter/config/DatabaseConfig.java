package com.manhpd.jpaconverter.config;


import com.viettel.security.PassTranformer;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.manhpd.jpaconverter.persistence.repository")
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    private final String PROPERTY_DRIVER = "driver";
    private final String PROPERTY_URL = "url";
    private final String PROPERTY_USERNAME = "user";
    private final String PROPERTY_PASSWORD = "password";
    private final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
    private final String PROPERTY_DIALECT = "hibernate.dialect";
    private final String PROPERTY_DDL_AUTO = "hibernate.ddl-auto";

    @Autowired
    private Environment env;

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lfb.setPackagesToScan("com.manhpd.jpaconverter.persistence.entity");
        lfb.setJpaProperties(hibernateProps());

        return lfb;
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(this.env.getProperty(PROPERTY_URL));
        ds.setUsername(this.getRealValue(this.env.getProperty(PROPERTY_USERNAME)));
        ds.setPassword(this.getRealValue(this.env.getProperty(PROPERTY_PASSWORD)));
        ds.setDriverClassName(this.env.getProperty(PROPERTY_DRIVER));

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

    String getRealValue(String hash) {
        System.out.println("Hash value is: " + hash);
        String value = PassTranformer.decrypt(hash);
        System.out.println("The decrypted value is: " + value);

        return value;
    }

}
