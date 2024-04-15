package com.api.shopadminservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.shopadminservice.entity.Admin;
import com.api.shopadminservice.request.AdminRequest;
import com.api.shopadminservice.response.AdminResponse;
import com.api.shopadminservice.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@PostMapping("/create")
	public AdminResponse create(@RequestBody AdminRequest adminRequest) {
		return service.create(adminRequest);
	}
	
	@GetMapping("/getbyid/{id}")
	public AdminResponse getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@GetMapping("/getall")
	public List<Admin> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/update/{id}")
	public AdminResponse update(@PathVariable int id, @RequestBody Admin updatedAdminDetails) {
		return service.update(id, updatedAdminDetails);
	}
	
	@GetMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable int id) {
		return service.delete(id);
	}
	
}
