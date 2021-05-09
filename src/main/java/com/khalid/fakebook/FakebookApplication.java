package com.khalid.fakebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FakebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakebookApplication.class, args);
	}

}
