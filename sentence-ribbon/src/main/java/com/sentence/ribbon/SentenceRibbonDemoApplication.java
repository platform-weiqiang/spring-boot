package com.sentence.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SentenceRibbonDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SentenceRibbonDemoApplication.class, args);
	}
}
