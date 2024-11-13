package com.example.exerice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Exerice1Application {

    public static void main(String[] args) {
        SpringApplication.run(Exerice1Application.class, args);
    }

}
