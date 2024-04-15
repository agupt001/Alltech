package com.api.shopcartservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.shopcartservice.entity.Cart;
import com.api.shopcartservice.request.CartRequest;
import com.api.shopcartservice.response.ProductResponse;
import com.api.shopcartservice.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService service;
	
	@PostMapping("/create")
	public Cart create(@RequestBody CartRequest request) {
		return service.create(request);
	}
	
	@GetMapping("/getbyuserid/{id}")
	public List<ProductResponse> get(@PathVariable("id") int user_id) {
		return service.get(user_id);
	}
	
	@PostMapping("/update")
	public Cart update(@RequestBody CartRequest request) {
		return service.updateQuantity(request);
	}
	
	@PostMapping("/delete")
	public Map<String, Boolean> delete(@RequestBody CartRequest request) {
		return service.delete(request);
	}
}
