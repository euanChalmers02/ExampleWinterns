package com.example.mdbspringboot;

import com.example.mdbspringboot.repository.ItemRepository;
import com.example.mdbspringboot.repository.NatwestApiClient;
import com.example.mdbspringboot.services.UserInsightsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.json.*;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;

@SpringBootTest
class MdbSpringBootApplicationTests {
    @Autowired
    ItemRepository repo;

	@Test
	void contextLoads() throws URISyntaxException, IOException, InterruptedException {
		var accID = "fb246b90-e549-44ef-831e-ea7fe8cf88c9";
		var transactionID = "bd5e0498-21a3-400e-aa2d-1e747c16485f";
	}

}
