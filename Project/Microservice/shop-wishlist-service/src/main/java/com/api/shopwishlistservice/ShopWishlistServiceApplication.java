package com.api.shopwishlistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopWishlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopWishlistServiceApplication.class, args);
	}

}
