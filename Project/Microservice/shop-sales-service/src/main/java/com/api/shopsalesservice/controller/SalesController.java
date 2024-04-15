package com.api.shopsalesservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.shopsalesservice.entity.Sales;
import com.api.shopsalesservice.request.SalesRequest;
import com.api.shopsalesservice.response.AdminResponse;
import com.api.shopsalesservice.response.SalesResponse;
import com.api.shopsalesservice.service.SalesService;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
	
	@Autowired
	private SalesService service;
	
	@PostMapping("/create")
	public SalesResponse create(@RequestBody SalesRequest request) {
		return service.create(request);
	}
	
	@PostMapping("/createwithoutdiscount")
	public SalesResponse createWithoutDiscount(@RequestBody SalesRequest request) {
		return service.createWithoutDiscount(request);
	}
	
	@GetMapping("/getbyuserid/{id}")
	public List<AdminResponse> getByUserId(@PathVariable("id") int user_id) {
		return service.getByUserId(user_id);
	}
	
	@GetMapping("/getall")
	public List<Sales> getAll() {
		return service.getAll();
	}

}
