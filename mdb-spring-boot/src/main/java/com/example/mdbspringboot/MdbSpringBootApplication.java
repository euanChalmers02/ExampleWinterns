package com.example.mdbspringboot;

import com.example.mdbspringboot.repository.NatwestApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.net.URISyntaxException;


@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication{
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

//		add getting account details and getting transactions;
//		possibly make interface to make nicer to understand
//		NatwestApiClient.getByUsername("djefferson");
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
}

