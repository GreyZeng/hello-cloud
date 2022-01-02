package com.example.restfulpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RestfulPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulPracticeApplication.class, args);
    }

}
