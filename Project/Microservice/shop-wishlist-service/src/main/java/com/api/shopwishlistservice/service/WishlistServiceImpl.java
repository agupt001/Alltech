package com.api.shopwishlistservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.shopwishlistservice.entity.Wishlist;
import com.api.shopwishlistservice.feignClients.WishlistFeignClient;
import com.api.shopwishlistservice.repository.WishlistRepository;
import com.api.shopwishlistservice.request.WishlistRequest;
import com.api.shopwishlistservice.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository repo;
	
	@Autowired
	private WishlistFeignClient client;
	
	@Override
	@Transactional
	public Wishlist create(WishlistRequest request) {
		
		Wishlist wishlist = getWishlistFromRequest(request);
		return repo.save(wishlist);
		
	}

	@Override
	@Transactional
	public List<ProductResponse> get(int user_id) {
		
		List<Integer> prodIds = new ArrayList<Integer>();
		List<Wishlist> wishlists = repo.findByUserId(user_id);
		
		for (int i = 0; i < wishlists.size(); i++) {
			prodIds.add(wishlists.get(i).getProduct_id());
		}
		
		List<ProductResponse> response = client.getProductResponse(prodIds);
		
		return response;
	}

	@Override
	@Transactional
	public Map<String, Boolean> delete(WishlistRequest request) {
		
		int userId = request.getUser_id();
		int prod_id = request.getProduct_id();
		int wishlistIdToDelete = -1;
		
		List<Wishlist> wishlists = repo.findByUserId(userId);
		
		if(!wishlists.isEmpty()) {
			
			for (int i = 0; i < wishlists.size(); i++) {
				if(wishlists.get(i).getProduct_id() == prod_id) {
					wishlistIdToDelete = wishlists.get(i).getId();
				}
			}

			if(wishlistIdToDelete != -1) {
				repo.deleteById(wishlistIdToDelete);
				Map<String, Boolean> response = new HashMap<>();
	            response.put("Product deleted", Boolean.TRUE);
	            return response;
			}
		}
		
		// Handle the case where the wish list item does not exist
        Map<String, Boolean> response = new HashMap<>();
        response.put("Product not found", Boolean.FALSE);
        return response;
		
	}
	
	private Wishlist getWishlistFromRequest(WishlistRequest request) {
		
		return new Wishlist(
				request.getUser_id(),
				request.getProduct_id()
				);
	}

}
