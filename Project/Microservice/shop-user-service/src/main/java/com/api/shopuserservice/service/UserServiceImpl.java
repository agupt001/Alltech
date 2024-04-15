package com.api.shopuserservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.shopuserservice.entity.User;
import com.api.shopuserservice.repository.UserRepository;
import com.api.shopuserservice.request.UserRequest;
import com.api.shopuserservice.response.UserResponse;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	@Transactional
	public UserResponse create(UserRequest userRequest) {
		
		User user = getUserFromRequest(userRequest);
		User savedUser = repo.save(user);
		
		return getUserResponse(savedUser);
		
	}

	@Override
	@Transactional
	public UserResponse getById(int id) {
		
		User user = repo.findById(id).orElseThrow(()-> new RuntimeException("User ID not found!"));
		
		return getUserResponse(user);
		
	}

	@Override
	@Transactional
	public List<User> getAll() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public UserResponse update(int id, User updatedUserDetails) {
		
		User prevUser = repo.findById(id).orElseThrow(()-> new RuntimeException("User ID not found!"));
		
		prevUser.setName(updatedUserDetails.getName());
		prevUser.setEmail(updatedUserDetails.getEmail());
		prevUser.setPhone(updatedUserDetails.getPhone());
		prevUser.setUsername(updatedUserDetails.getUsername());
		prevUser.setPassword(updatedUserDetails.getPassword());
		
		User savedUser = repo.save(prevUser);
		
		return getUserResponse(savedUser);
	}

	@Override
	@Transactional
	public Map<String, Boolean> delete(int id) {
		
		User prevUser = repo.findById(id).orElseThrow(()-> new RuntimeException("User ID not found!"));
		repo.delete(prevUser);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("User deleted", Boolean.TRUE);
		return response;
		
	}
	
	private UserResponse getUserResponse(User savedUser){
		
		return new UserResponse(
				savedUser.getId(),
				savedUser.getName(),
				savedUser.getEmail(),
				savedUser.getPhone(),
				savedUser.getUsername(),
				savedUser.getPassword()
				);
		
	}
	
	private User getUserFromRequest(UserRequest userRequest) {
		
		return new User(
				userRequest.getName(),
				userRequest.getEmail(),
				userRequest.getPhone(),
				userRequest.getUsername(),
				userRequest.getPassword()
				);
	}

}
