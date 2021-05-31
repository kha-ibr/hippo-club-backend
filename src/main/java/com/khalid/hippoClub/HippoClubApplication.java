package com.khalid.hippoClub;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@AllArgsConstructor
public class HippoClubApplication {
	public static void main(String[] args) {
		SpringApplication.run(HippoClubApplication.class, args);
	}

}
