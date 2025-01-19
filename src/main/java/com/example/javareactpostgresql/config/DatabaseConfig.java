package com.example.javareactpostgresql.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        Dotenv dotenv = Dotenv.load();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        // AWS RDS configuration
        String dbUrl = String.format("jdbc:postgresql://%s:%s/%s",
                dotenv.get("DB_URL"),
                dotenv.get("DB_PORT"),
                dotenv.get("DB_NAME")
        );

        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dotenv.get("DB_USERNAME"));
        dataSource.setPassword(dotenv.get("DB_PASSWORD"));

        return dataSource;
    }
}

