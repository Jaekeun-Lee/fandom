package com.fc.fandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FandomUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FandomUserApplication.class, args);
    }

}
