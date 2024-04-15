package com.api.shopsalesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopSalesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopSalesServiceApplication.class, args);
	}

}
