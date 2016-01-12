package com.sw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Bem vindo ao lado negro da força!");
	}
}
