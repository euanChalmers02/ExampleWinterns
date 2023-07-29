package com.example.mdbspringboot;

import com.example.mdbspringboot.repository.NatwestApiClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.net.URISyntaxException;

@OpenAPIDefinition(
		info = @Info(
				title = "API NAME HERE",
				version = "v0.1",
				description = "Winterns 2023 Hackathon API"
		)
)

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication{

	public static void main(String[] args){

//		add getting account details and getting transactions;
//		possibly make interface to make nicer to understand
//		NatwestApiClient.getByUsername("djefferson");
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
}

