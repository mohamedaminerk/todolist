package com.medamine.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;

@SpringBootApplication
public class TodolistApplication {
    public static void main(String[] args) {
        System.out.print(LocalDate.now());
        SpringApplication.run(TodolistApplication.class, args);
    }
}
