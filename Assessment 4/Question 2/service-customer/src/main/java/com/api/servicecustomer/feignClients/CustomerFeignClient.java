package com.api.servicecustomer.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.servicecustomer.response.OrderResponse;

@FeignClient(name = "customer-feign-client", url = "${order-url}")
public interface CustomerFeignClient {

	@GetMapping("/{id}")
	OrderResponse getOrderById(@PathVariable("id") Integer id);
	
}
