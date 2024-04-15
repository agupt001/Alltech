package com.api.shopuserservice.service;

import java.util.List;
import java.util.Map;

import com.api.shopuserservice.entity.User;
import com.api.shopuserservice.request.UserRequest;
import com.api.shopuserservice.response.UserResponse;

public interface UserService {

	UserResponse create(UserRequest userRequest);
	UserResponse getById(int id);
	List<User> getAll();
	UserResponse update(int id, User updatedUserDetails);
	Map<String, Boolean> delete(int id);
	
}
