package com.example.onepieceanime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableMongoRepositories
public class OnePieceAnimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnePieceAnimeApplication.class, args);
    }

    @GetMapping()
    public String sayHello() {
        return String.format("Welcome to the website");
    }

}
