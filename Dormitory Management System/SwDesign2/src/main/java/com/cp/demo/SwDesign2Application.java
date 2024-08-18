package com.cp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan(basePackages = {"com.cp.model"})
@EnableJpaRepositories(basePackages = "com.cp.repository")
@ComponentScan(basePackages={"com.cp.demo","com.cp.model","com.cp.repository",
		"com.cp.controller", "com.cp.service"})
public class SwDesign2Application {

	public static void main(String[] args) {
		SpringApplication.run(SwDesign2Application.class, args);
	}

}
