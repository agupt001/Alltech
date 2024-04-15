package com.api.shopwishlistservice.service;

import java.util.List;
import java.util.Map;

import com.api.shopwishlistservice.entity.Wishlist;
import com.api.shopwishlistservice.request.WishlistRequest;
import com.api.shopwishlistservice.response.ProductResponse;

public interface WishlistService {

	Wishlist create(WishlistRequest request);
	List<ProductResponse> get(int user_id);
	Map<String, Boolean> delete(WishlistRequest request);
	
}
