package com.nagarro.nottakingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotetakingappApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotetakingappApplication.class, args);
	}

}
