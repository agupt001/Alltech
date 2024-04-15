package com.api.shopuserservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.shopuserservice.entity.User;
import com.api.shopuserservice.request.UserRequest;
import com.api.shopuserservice.response.UserResponse;
import com.api.shopuserservice.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/create")
	public UserResponse create(@RequestBody UserRequest userRequest) {
		return service.create(userRequest);
	}
	
	@GetMapping("/getbyid/{id}")
	public UserResponse getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@GetMapping("/getall")
	public List<User> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/update/{id}")
	public UserResponse update(@PathVariable int id, @RequestBody User updatedUserDetails) {
		return service.update(id, updatedUserDetails);
	}
	
	@GetMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable int id) {
		return service.delete(id);
	}
	
}
