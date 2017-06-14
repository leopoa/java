package com.amarilho.springboothystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableAutoConfiguration
@org.springframework.web.bind.annotation.RestController
@SpringBootApplication
public class RestController {

    @Autowired
    private ServiceApi service;

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    @RequestMapping("/")
    public String call() {
        return service.callApi();
    }

    public static void main(String[] args) {
        SpringApplication.run(RestController.class, args);
    }
}
