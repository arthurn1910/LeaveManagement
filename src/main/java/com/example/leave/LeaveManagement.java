package com.example.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class LeaveManagement {

	public static void main(String[] args) {
		SpringApplication.run(LeaveManagement.class, args);
	}
}
