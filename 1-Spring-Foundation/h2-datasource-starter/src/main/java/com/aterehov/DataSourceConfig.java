package com.aterehov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    public static final String H2_DRIVER_CLASS_NAME = "org.h2.Driver";
    @Autowired
    private Environment environment;

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(H2_DRIVER_CLASS_NAME);
        dataSource.setUrl(environment.getProperty("h2-datasource.url"));
        dataSource.setUsername(environment.getProperty("h2-datasource.username"));
        dataSource.setPassword(environment.getProperty("h2-datasource.password"));

        return dataSource;
    }

}
