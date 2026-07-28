package com.abhi.socialMedia.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.mock.env.MockEnvironment;

class DataSourceEnvironmentPostProcessorTest {

    @Test
    void setsMysqlDriverAndDialectWhenDbUrlIsMysql() {
        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("DB_URL", "jdbc:mysql://example.com:3306/defaultdb");

        DataSourceEnvironmentPostProcessor postProcessor = new DataSourceEnvironmentPostProcessor();
        postProcessor.postProcessEnvironment(environment, new SpringApplication());

        assertEquals("com.mysql.cj.jdbc.Driver", environment.getProperty("spring.datasource.driver-class-name"));
        assertEquals("org.hibernate.dialect.MySQLDialect", environment.getProperty("spring.jpa.database-platform"));
    }

    @Test
    void defaultsToH2DriverAndDialectWhenDbUrlIsH2() {
        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("DB_URL", "jdbc:h2:mem:socialmedia");

        DataSourceEnvironmentPostProcessor postProcessor = new DataSourceEnvironmentPostProcessor();
        postProcessor.postProcessEnvironment(environment, new SpringApplication());

        assertEquals("org.h2.Driver", environment.getProperty("spring.datasource.driver-class-name"));
        assertEquals("org.hibernate.dialect.H2Dialect", environment.getProperty("spring.jpa.database-platform"));
    }
}
