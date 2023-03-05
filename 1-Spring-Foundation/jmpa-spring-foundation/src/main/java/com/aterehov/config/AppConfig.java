package com.aterehov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(this.environment.getProperty("h2-datasource.url"));
        dataSource.setUsername(this.environment.getProperty("h2-datasource.username"));
        dataSource.setPassword(this.environment.getProperty("h2-datasource.password"));
        return dataSource;
    }
}
