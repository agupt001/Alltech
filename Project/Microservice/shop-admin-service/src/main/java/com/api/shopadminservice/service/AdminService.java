package com.api.shopadminservice.service;

import java.util.List;
import java.util.Map;

import com.api.shopadminservice.entity.Admin;
import com.api.shopadminservice.request.AdminRequest;
import com.api.shopadminservice.response.AdminResponse;

public interface AdminService {

	AdminResponse create(AdminRequest adminRequest);
	AdminResponse getById(int id);
	List<Admin> getAll();
	AdminResponse update(int id, Admin updatedAdminDetails);
	Map<String, Boolean> delete(int id);
	
}
