package com.api.shopdiscountsservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.shopdiscountsservice.entity.Discount;
import com.api.shopdiscountsservice.request.DiscountRequest;
import com.api.shopdiscountsservice.response.DiscountResponse;
import com.api.shopdiscountsservice.service.DiscountService;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

	@Autowired
	private DiscountService service;
	
	@PostMapping("/create")
	public Discount create(@RequestBody DiscountRequest request) {
		return service.create(request);
	}
	
	@GetMapping("/getbyuserid/{id}")
	public List<DiscountResponse> getByUserId(@PathVariable("id") int user_id) {
		return service.getByUserId(user_id);
	}
	
	@GetMapping("/getall")
	public List<Discount> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/update")
	public Discount update(@RequestBody DiscountRequest request) {
		return service.update(request);
	}
	
	@PostMapping("/delete")
	public Map<String, Boolean> delete(@RequestBody DiscountRequest request) {
		return service.delete(request);
	}
}
