package com.mini.coffeenpastebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CoffeeNPasteBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeNPasteBeApplication.class, args);
    }

}
