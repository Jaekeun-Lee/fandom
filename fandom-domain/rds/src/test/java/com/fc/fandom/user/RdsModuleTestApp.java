package com.fc.fandom.user;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

@SpringBootApplication
@EnableJpaAuditing
public class RdsModuleTestApp {
    public static void main(String[] args) {
        SpringApplication.run(RdsModuleTestApp.class, args);
    }

    @Configuration
    @ComponentScan
    @EnableJpaRepositories
    @EntityScan
    class TestConfig {

        @Bean
        public Server h2TcpServer() throws SQLException {
            return Server.createTcpServer().start();
        }

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}
