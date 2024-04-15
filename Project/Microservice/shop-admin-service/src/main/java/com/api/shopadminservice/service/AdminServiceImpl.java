package com.api.shopadminservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.shopadminservice.entity.Admin;
import com.api.shopadminservice.repository.AdminRepository;
import com.api.shopadminservice.request.AdminRequest;
import com.api.shopadminservice.response.AdminResponse;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;
	
	@Override
	@Transactional
	public AdminResponse create(AdminRequest adminRequest) {
		
		Admin admin = getAdminFromRequest(adminRequest);
		Admin savedUser = repo.save(admin);
		
		return getAdminResponse(savedUser);
	}

	@Override
	@Transactional
	public AdminResponse getById(int id) {
		
		Admin admin = repo.findById(id).orElseThrow(()-> new RuntimeException("Admin ID not found!"));
		
		return getAdminResponse(admin);
	}

	@Override
	@Transactional
	public List<Admin> getAll() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public AdminResponse update(int id, Admin updatedAdminDetails) {

		Admin prevAdmin = repo.findById(id).orElseThrow(()-> new RuntimeException("Admin ID not found!"));
		
		prevAdmin.setName(updatedAdminDetails.getName());
		prevAdmin.setEmail(updatedAdminDetails.getEmail());
		prevAdmin.setPhone(updatedAdminDetails.getPhone());
		prevAdmin.setPassword(updatedAdminDetails.getPassword());
		
		Admin savedAdmin = repo.save(prevAdmin);
		
		return getAdminResponse(savedAdmin);
	}

	@Override
	@Transactional
	public Map<String, Boolean> delete(int id) {

		repo.deleteById(id);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Admin deleted", Boolean.TRUE);
		return response;
	}
	
	private AdminResponse getAdminResponse(Admin savedAdmin){
		
		return new AdminResponse(
				savedAdmin.getId(),
				savedAdmin.getName(),
				savedAdmin.getEmail(),
				savedAdmin.getPhone(),
				savedAdmin.getUsername(),
				savedAdmin.getPassword()
				);
		
	}
	
	private Admin getAdminFromRequest(AdminRequest adminRequest) {
		
		return new Admin(
				adminRequest.getName(),
				adminRequest.getEmail(),
				adminRequest.getPhone(),
				adminRequest.getUsername(),
				adminRequest.getPassword()
				);
	}

}
