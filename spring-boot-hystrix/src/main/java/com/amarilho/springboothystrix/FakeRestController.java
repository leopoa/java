package com.amarilho.springboothystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@org.springframework.web.bind.annotation.RestController
public class FakeRestController {


    @RequestMapping("/fake")
    public String fake() {
        return "Bombou!";
    }

    public static void main(String[] args) {
        System.getProperties().put( "server.port", 8090 );
        SpringApplication.run(FakeRestController.class, args);
    }
}
