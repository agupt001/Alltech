package com.api.shopcartservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.shopcartservice.entity.Cart;
import com.api.shopcartservice.feignClients.CartFeignClient;
import com.api.shopcartservice.repository.CartRepository;
import com.api.shopcartservice.request.CartRequest;
import com.api.shopcartservice.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repo;
	
	@Autowired
	private CartFeignClient client;
	
	@Override
	@Transactional
	public Cart create(CartRequest request) {

		Cart cart = getCartFromRequest(request);
		return repo.save(cart);
	}

	@Override
	@Transactional
	public List<ProductResponse> get(int user_id) {
		
		List<Integer> prodIds = new ArrayList<Integer>();
		List<Cart> cart = repo.findByUserId(user_id);
		
		for (int i = 0; i < cart.size(); i++) {
			prodIds.add(cart.get(i).getProduct_id());
		}
		
		List<ProductResponse> response = client.getProductResponse(prodIds);
		
		for (int i = 0; i < response.size(); i++) {
			for (int j = 0; j < cart.size(); j++) {
				
				if(response.get(i).getId() == cart.get(j).getProduct_id()) {
					response.get(i).setQuantity(cart.get(j).getQuantity());
				}
			}
			
		}
		
		return response;
	}

	@Override
	@Transactional
	public Map<String, Boolean> delete(CartRequest request) {
		
		int userId = request.getUserId();
		int prod_id = request.getProduct_id();
		int cartIdToDelete = -1;
		
		List<Cart> carts = repo.findByUserId(userId);
		
		if(!carts.isEmpty()) {
			
			for (int i = 0; i < carts.size(); i++) {
				if(carts.get(i).getProduct_id() == prod_id) {
					cartIdToDelete = carts.get(i).getId();
				}
			}

			if(cartIdToDelete != -1) {
				repo.deleteById(cartIdToDelete);
				Map<String, Boolean> response = new HashMap<>();
	            response.put("Product deleted", Boolean.TRUE);
	            return response;
			}
		}
		
		// Handle the case where the cart item does not exist
        Map<String, Boolean> response = new HashMap<>();
        response.put("Product not found", Boolean.FALSE);
        return response;
	}
	
	private Cart getCartFromRequest(CartRequest request) {
		
		return new Cart(
				request.getUserId(),
				request.getProduct_id(),
				request.getQuantity()
				);
	}

	@Override
	@Transactional
	public Cart updateQuantity(CartRequest request) {

		int userId = request.getUserId();
		int prod_id = request.getProduct_id();
		Cart cartToUpdate = new Cart();
		
		List<Cart> carts = repo.findByUserId(userId);
		
		if(!carts.isEmpty()) {
			
			for (int i = 0; i < carts.size(); i++) {
				if(carts.get(i).getProduct_id() == prod_id) {
					cartToUpdate = carts.get(i);
				}
			}

			if(cartToUpdate != null) {
				
				cartToUpdate.setQuantity(request.getQuantity());
				return repo.save(cartToUpdate);
				
			}
		}
		
		// Handle the case where the cart item does not exist
        
        return null;
	}

}
