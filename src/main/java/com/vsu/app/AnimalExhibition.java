package com.vsu.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.vsu")
@ComponentScan(basePackages = {"com.vsu"})
public class AnimalExhibition {
    public static void main(String[] args) {
        SpringApplication.run(AnimalExhibition.class,args);
    }
}
