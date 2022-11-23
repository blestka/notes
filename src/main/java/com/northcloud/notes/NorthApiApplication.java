package com.northcloud.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NorthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthApiApplication.class, args);
    }

}
