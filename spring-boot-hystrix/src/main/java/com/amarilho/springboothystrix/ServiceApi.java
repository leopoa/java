package com.amarilho.springboothystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@EnableHystrix
@Service
public class ServiceApi {

    private final RestTemplate restTemplate;

    public ServiceApi(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1"),
            //@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000")
    })
    public String callApi() {
        System.out.println("CALL API");
        URI uri = URI.create("http://localhost:8090/fake");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String fallbackMethod() {
        System.out.println("CALLBACK: Fora do Ar!");
        return "Fora do ar!";
    }

}
