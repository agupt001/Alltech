package com.api.shopdiscountsservice.service;

import java.util.List;
import java.util.Map;

import com.api.shopdiscountsservice.entity.Discount;
import com.api.shopdiscountsservice.request.DiscountRequest;
import com.api.shopdiscountsservice.response.DiscountResponse;

public interface DiscountService {

	Discount create(DiscountRequest request);
	List<DiscountResponse> getByUserId(int user_id);
	List<Discount> getAll();
	Discount update(DiscountRequest request);
	Map<String, Boolean> delete(DiscountRequest request);
	
}
