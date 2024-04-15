package com.api.shopsalesservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.shopsalesservice.entity.Sales;
import com.api.shopsalesservice.feignClients.SalesFeignClient;
import com.api.shopsalesservice.repository.SalesRepository;
import com.api.shopsalesservice.request.SalesRequest;
import com.api.shopsalesservice.response.AdminResponse;
import com.api.shopsalesservice.response.ProductResponse;
import com.api.shopsalesservice.response.SalesResponse;

@Service
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SalesRepository repo;
	
	@Autowired
	private SalesFeignClient client;
	
	@Override
	public SalesResponse create(SalesRequest salesRequest) {
		
		Sales sale = getProductFromRequest(salesRequest);
		Sales savedSale = repo.save(sale);
		
		return getProductResponse(savedSale);
	}
	
	@Override
	public SalesResponse createWithoutDiscount(SalesRequest salesRequest) {
		
		Sales sale = getProductFromRequestWithoutDiscount(salesRequest);
		Sales savedSale = repo.save(sale);
		
		return getProductResponseWithoutDiscount(savedSale);
	}

	@Override
	public List<AdminResponse> getByUserId(int user_id) {
		
		List<Integer> prodIds = new ArrayList<Integer>();
		List<Sales> sales = repo.findByUserId(user_id);
		
		if(!sales.isEmpty()) {
			
			for (Sales sale : sales) {
				prodIds.add(sale.getProduct_id());
			}
		}
		
		List<ProductResponse> prodResponse = client.getProductResponse(prodIds);
		List<AdminResponse> response = new ArrayList<AdminResponse>();
		
		if(!prodResponse.isEmpty()) {
			for (ProductResponse product : prodResponse) {
				for (Sales sale : sales) {
				
					if(product.getId() == sale.getProduct_id()) {
						AdminResponse adminResponse = new AdminResponse(product, getProductResponse(sale));
						response.add(adminResponse);
					}
					
				}
			}
			
		}
		
		return response;
	}

	@Override
	public List<Sales> getAll() {
		
		return repo.findAll();
	}
	
	private SalesResponse getProductResponse(Sales savedSales){
		
		return new SalesResponse(
				savedSales.getId(),
				savedSales.getUserId(),
				savedSales.getProduct_id(),
				savedSales.getProduct_name(),
				savedSales.getProduct_category(),
				savedSales.getDiscount_id(),
				savedSales.getProduct_price(),
				savedSales.getDiscount(),
				savedSales.getFinal_price(),
				savedSales.getQuantity(),
				savedSales.getCreated_date()
				);
		
	}
	
	private SalesResponse getProductResponseWithoutDiscount(Sales savedSales){
		
		return new SalesResponse(
				savedSales.getId(),
				savedSales.getUserId(),
				savedSales.getProduct_id(),
				savedSales.getProduct_name(),
				savedSales.getProduct_category(),
				savedSales.getProduct_price(),
				savedSales.getFinal_price(),
				savedSales.getQuantity(),
				savedSales.getCreated_date()
				);
		
	}
	
	private Sales getProductFromRequest(SalesRequest salesRequest) {
		
		return new Sales(
				salesRequest.getUserId(),
				salesRequest.getProduct_id(),
				salesRequest.getProduct_name(),
				salesRequest.getProduct_category(),
				salesRequest.getDiscount_id(),
				salesRequest.getProduct_price(),
				salesRequest.getDiscount(),
				salesRequest.getFinal_price(),
				salesRequest.getQuantity()
				);
		
	}
	
	private Sales getProductFromRequestWithoutDiscount(SalesRequest salesRequest) {
		
		return new Sales(
				salesRequest.getUserId(),
				salesRequest.getProduct_id(),
				salesRequest.getProduct_name(),
				salesRequest.getProduct_category(),
				salesRequest.getProduct_price(),
				salesRequest.getFinal_price(),
				salesRequest.getQuantity()
				);
	}

}
