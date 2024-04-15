package com.api.shopdiscountsservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.shopdiscountsservice.entity.Discount;
import com.api.shopdiscountsservice.feignClient.DiscountFeignClient;
import com.api.shopdiscountsservice.repository.DiscountRepository;
import com.api.shopdiscountsservice.request.DiscountRequest;
import com.api.shopdiscountsservice.response.DiscountResponse;
import com.api.shopdiscountsservice.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountRepository repo;
	
	@Autowired
	private DiscountFeignClient client;
	
	@Override
	@Transactional
	public Discount create(DiscountRequest request) {
		
		Discount discount = getDiscountFromRequest(request);
		return repo.save(discount);
	}

	@Override
	@Transactional
	public List<DiscountResponse> getByUserId(int user_id) {
		
		List<Integer> prodIds = new ArrayList<Integer>();
		List<Discount> discount = repo.findByUserId(user_id);
		
		if(!discount.isEmpty()) {
			for (int i = 0; i < discount.size(); i++) {
				prodIds.add(discount.get(i).getProduct_id());
			}
		}
		
		List<ProductResponse> prodResponse = client.getProductResponse(prodIds);
		List<DiscountResponse> response = new ArrayList<DiscountResponse>();
		
		if(!prodResponse.isEmpty()) {
			for (int i = 0; i < prodResponse.size(); i++) {
				for (int j = 0; j < discount.size(); j++) {
					
					if(prodResponse.get(i).getId() == discount.get(j).getProduct_id()) {
						
						DiscountResponse dr = new DiscountResponse(discount.get(j).getId(), prodResponse.get(i), discount.get(j).getDiscount_price());
						response.add(dr);
						
					}
				}
				
			}
		}
		
		return response;
	}

	@Override
	@Transactional
	public List<Discount> getAll() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public Discount update(DiscountRequest request) {
		
		int userId = request.getUserId();
		int prod_id = request.getProduct_id();
		Discount discountToUpdate = new Discount();
		
		List<Discount> discount = repo.findByUserId(userId);
		
		if(!discount.isEmpty()) {
			
			for (int i = 0; i < discount.size(); i++) {
				if(discount.get(i).getProduct_id() == prod_id) {
					discountToUpdate = discount.get(i);
				}
			}

			if(discountToUpdate != null) {
				
				discountToUpdate.setDiscount_price(request.getDiscount_price());
				return repo.save(discountToUpdate);
				
			}
		}
		
		// Handle the case where the cart item does not exist
        
        return null;
	}

	@Override
	@Transactional
	public Map<String, Boolean> delete(DiscountRequest request) {
		
		int userId = request.getUserId();
		int prod_id = request.getProduct_id();
		int cartIdToDelete = -1;
		
		List<Discount> discount = repo.findByUserId(userId);
		
		if(!discount.isEmpty()) {
			
			for (int i = 0; i < discount.size(); i++) {
				if(discount.get(i).getProduct_id() == prod_id) {
					cartIdToDelete = discount.get(i).getId();
				}
			}

			if(cartIdToDelete != -1) {
				repo.deleteById(cartIdToDelete);
				Map<String, Boolean> response = new HashMap<>();
	            response.put("Discount deleted", Boolean.TRUE);
	            return response;
			}
		}
		
		// Handle the case where the cart item does not exist
        Map<String, Boolean> response = new HashMap<>();
        response.put("Discount not found", Boolean.FALSE);
        return response;
	}
	
	private Discount getDiscountFromRequest(DiscountRequest request) {
		
		return new Discount(
				request.getUserId(),
				request.getProduct_id(),
				request.getDiscount_price()
				);
	}

}
