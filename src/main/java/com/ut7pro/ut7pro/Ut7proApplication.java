package com.ut7pro.ut7pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaAuditing
@RestController
public class Ut7proApplication {
    
        @GetMapping("/api/v1/")
        public String hello() {
            return "Hello Api Spring Bootiful!";
        }

	public static void main(String[] args) {
            SpringApplication.run(Ut7proApplication.class, args);
	}

}
