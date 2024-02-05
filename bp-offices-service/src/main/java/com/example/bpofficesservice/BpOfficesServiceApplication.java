package com.example.bpofficesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BpOfficesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpOfficesServiceApplication.class, args);
	}

}
