package com.api.shopwishlistservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.shopwishlistservice.entity.Wishlist;
import com.api.shopwishlistservice.request.WishlistRequest;
import com.api.shopwishlistservice.response.ProductResponse;
import com.api.shopwishlistservice.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService service;
	
	@PostMapping("/create")
	public Wishlist create(@RequestBody WishlistRequest request) {
		return service.create(request);
	}
	
	@GetMapping("/getbyuserid/{id}")
	public List<ProductResponse> get(@PathVariable("id") int user_id) {
		return service.get(user_id);
	}
	
	@PostMapping("/delete")
	public Map<String, Boolean> delete(@RequestBody WishlistRequest request) {
		return service.delete(request);
	}
}
