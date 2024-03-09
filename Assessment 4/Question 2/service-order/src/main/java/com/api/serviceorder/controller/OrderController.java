package com.api.serviceorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.serviceorder.response.OrderResponse;
import com.api.serviceorder.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@GetMapping("/get/{id}")
	public OrderResponse getOrderById(@PathVariable("id") int id) {
		
		return service.getOrder(id);
		
	}
}
