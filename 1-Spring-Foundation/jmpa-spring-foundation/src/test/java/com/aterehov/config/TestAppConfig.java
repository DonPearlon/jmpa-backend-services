package com.aterehov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class TestAppConfig {
    @Autowired
    private Environment environment;

    @Bean
    @Profile("test")
    public DataSource testDataSource() {
        DriverManagerDataSource testDataSource = new DriverManagerDataSource();
        testDataSource.setDriverClassName("org.h2.Driver");
        testDataSource.setUrl(this.environment.getProperty("h2-datasource.url"));
        testDataSource.setUsername(this.environment.getProperty("h2-datasource.username"));
        testDataSource.setPassword(this.environment.getProperty("h2-datasource.password"));
        return testDataSource;
    }
}
