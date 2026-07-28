package com.abhi.socialMedia.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

public class DataSourceEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String dbUrl = environment.getProperty("DB_URL");
        if (dbUrl == null || dbUrl.isBlank()) {
            dbUrl = environment.getProperty("DATABASE_URL");
        }
        if (dbUrl == null || dbUrl.isBlank()) {
            dbUrl = environment.getProperty("spring.datasource.url");
        }

        if (dbUrl == null || dbUrl.isBlank()) {
            return;
        }

        Map<String, Object> properties = new HashMap<>();

        if (dbUrl.startsWith("jdbc:mysql")) {
            properties.put("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
            properties.put("spring.jpa.database-platform", "org.hibernate.dialect.MySQLDialect");
        } else if (dbUrl.startsWith("jdbc:postgresql")) {
            properties.put("spring.datasource.driver-class-name", "org.postgresql.Driver");
            properties.put("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQLDialect");
        } else {
            properties.put("spring.datasource.driver-class-name", "org.h2.Driver");
            properties.put("spring.jpa.database-platform", "org.hibernate.dialect.H2Dialect");
        }

        environment.getPropertySources().addFirst(new MapPropertySource("datasourceEnvironmentPostProcessor", properties));
    }
}
