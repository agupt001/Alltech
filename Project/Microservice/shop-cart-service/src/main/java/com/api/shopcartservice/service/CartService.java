package com.api.shopcartservice.service;

import java.util.List;
import java.util.Map;

import com.api.shopcartservice.entity.Cart;
import com.api.shopcartservice.request.CartRequest;
import com.api.shopcartservice.response.ProductResponse;


public interface CartService {

	Cart create(CartRequest request);
	List<ProductResponse> get(int user_id);
	Cart updateQuantity(CartRequest request);
	Map<String, Boolean> delete(CartRequest request);
	
}
