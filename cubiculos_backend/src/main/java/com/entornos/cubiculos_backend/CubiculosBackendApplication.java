package com.entornos.cubiculos_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CubiculosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CubiculosBackendApplication.class, args);
	}

}
