package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.revature.models")
@ComponentScan("com.revature")
public class PairedProgrammingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PairedProgrammingProjectApplication.class, args);
	}

}
