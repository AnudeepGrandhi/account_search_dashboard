package com.example.bpzipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class BpZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpZipkinServerApplication.class, args);
	}

}
