package com.example.AquaGuide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AquaGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(AquaGuideApplication.class, args);
	}

}
