package com.doubtsharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DoubtSharingAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoubtSharingAppBackendApplication.class, args);
	}

}
