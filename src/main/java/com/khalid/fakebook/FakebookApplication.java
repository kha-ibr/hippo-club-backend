package com.khalid.fakebook;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@AllArgsConstructor
public class FakebookApplication {
	public static void main(String[] args) {
		SpringApplication.run(FakebookApplication.class, args);
	}

}
