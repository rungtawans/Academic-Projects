package com.cp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
//@EntityScan(basePackages = {"com.cp.demo"})
//@EnableJpaRepositories(basePackages = "com.cp.demo")
//@ComponentScan(basePackages={"com.cp.demo"})
public class Lab9OneToManySec32023Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab9OneToManySec32023Application.class, args);
	}

}
