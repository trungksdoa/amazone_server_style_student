package com.student.project.amazone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
<<<<<<< HEAD
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
=======
>>>>>>> f196a1a75d7f3dfedcf321b5519171652b4dacf8

@SpringBootApplication
public class AmazoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazoneApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");


        };
    }

}
