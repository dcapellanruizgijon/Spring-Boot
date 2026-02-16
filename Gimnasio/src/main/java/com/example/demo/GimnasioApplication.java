package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching	//para la mejora del cahe
public class GimnasioApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GimnasioApplication.class, args);
	}

}
