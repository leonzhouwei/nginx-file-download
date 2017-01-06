package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppTest {

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(AppTest.class);
		sa.setAdditionalProfiles("test");
		ConfigurableApplicationContext ctx = sa.run(args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		for (String profile : activeProfiles) {
			System.err.println("Spring Boot 使用profile为: " + profile);
		}
	}

}
