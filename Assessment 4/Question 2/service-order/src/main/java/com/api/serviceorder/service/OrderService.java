package com.api.serviceorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.serviceorder.entity.Order;
import com.api.serviceorder.repository.OrderRepository;
import com.api.serviceorder.response.OrderResponse;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	public OrderResponse getOrder(int id) {
		
		Order order = orderRepo.findById(id).orElseThrow(()->new RuntimeException("ID not found!"));
		OrderResponse response = new OrderResponse();
		
		response.setId(order.getId());
		response.setOrderName(order.getOrderName());
		response.setOrderPrice(order.getOrderPrice());
		
		return response;
	}
	
}
