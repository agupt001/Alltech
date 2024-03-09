package com.api.servicecustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.servicecustomer.feignClients.CustomerFeignClient;
import com.api.servicecustomer.response.CustomerResponse;
import com.api.servicecustomer.response.OrderResponse;
import com.api.servicecustomer.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@Autowired
	private CustomerFeignClient customerFeignClient;
	
	@GetMapping("/get/{id}")
	public CustomerResponse getCustomerById(@PathVariable("id") Integer id) {
		
		CustomerResponse response = service.getCustomer(id);
		
		// Attach order response from feign client
		OrderResponse orderResponse = customerFeignClient.getOrderById(id);
		response.setOrderResponse(orderResponse);
		
		return response;
	}
	
}
