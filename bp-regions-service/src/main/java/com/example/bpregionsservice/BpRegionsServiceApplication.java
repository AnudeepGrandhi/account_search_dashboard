package com.example.bpregionsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BpRegionsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpRegionsServiceApplication.class, args);
	}

}
