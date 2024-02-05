package com.example.bpconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BpConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpConfigServerApplication.class, args);
	}

}
