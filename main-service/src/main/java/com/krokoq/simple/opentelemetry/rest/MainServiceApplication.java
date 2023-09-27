package com.krokoq.simple.opentelemetry.rest;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainServiceApplication {

	public static void main(String[] args) {
		ApplicationInsights.attach();
		SpringApplication.run(MainServiceApplication.class, args);
	}

}
