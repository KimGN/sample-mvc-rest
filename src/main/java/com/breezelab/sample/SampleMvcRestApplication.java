package com.breezelab.sample; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.breezelab")
public class SampleMvcRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleMvcRestApplication.class, args);
	}
}
