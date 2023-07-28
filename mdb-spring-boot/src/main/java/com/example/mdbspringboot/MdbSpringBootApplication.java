package com.example.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication{
	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
}

