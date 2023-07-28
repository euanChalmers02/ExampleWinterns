package com.example.mdbspringboot;

import org.springframework.web.client.RestTemplate;

public class DummyAPI{
    public static void getEmployees()
    {
        final String uri = "https://dummyjson.com/users/10";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }

}

