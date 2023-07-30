package com.example.mdbspringboot;

import com.example.mdbspringboot.repository.NatwestApiClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.net.URISyntaxException;

@OpenAPIDefinition(
		info = @Info(
				title = "OpenSpend Insights (OSI) API",
				version = "v0.1",
				description = "Winterns 2023 Hackathon API"
		)
)

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication{

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

//		add getting account details and getting transactions;
//		possibly make interface to make nicer to understand
		System.out.println(NatwestApiClient.getByAccountTransacationData("djefferson","5479a843-1aea-4317-b0fc-a45f1074342c"));
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
}

