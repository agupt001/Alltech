package com.api.shopsalesservice.service;

import java.util.List;

import com.api.shopsalesservice.entity.Sales;
import com.api.shopsalesservice.request.SalesRequest;
import com.api.shopsalesservice.response.AdminResponse;
import com.api.shopsalesservice.response.SalesResponse;

public interface SalesService {

	SalesResponse create(SalesRequest salesRequest);
	SalesResponse createWithoutDiscount(SalesRequest salesRequest);
	List<AdminResponse> getByUserId(int user_id);
	List<Sales> getAll();
	
}
