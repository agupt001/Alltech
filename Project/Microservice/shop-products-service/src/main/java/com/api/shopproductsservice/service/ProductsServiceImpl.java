package com.api.shopproductsservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.shopproductsservice.entity.Products;
import com.api.shopproductsservice.repository.ProductsRepository;
import com.api.shopproductsservice.request.ProductsRequest;
import com.api.shopproductsservice.response.ProductsResponse;

import jakarta.transaction.Transactional;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepository repo;
	
	@Override
	@Transactional
	public ProductsResponse create(ProductsRequest productRequest) {
		
		Products product = getProductFromRequest(productRequest);
		Products savedProduct = repo.save(product);
		
		return getProductResponse(savedProduct);
	}

	@Override
	@Transactional
	public ProductsResponse getById(int id) {

		Products product = repo.findById(id).orElseThrow(()-> new RuntimeException("Product ID not found!"));
		
		return getProductResponse(product);
	}

	@Override
	@Transactional
	public List<Products> getAll() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public ProductsResponse update(int id, Products updatedProductDetails) {

		Products prevProduct = repo.findById(id).orElseThrow(()-> new RuntimeException("Product ID not found!"));
		
		prevProduct.setName(updatedProductDetails.getName());
		prevProduct.setCategory(updatedProductDetails.getCategory());
		prevProduct.setPrice(updatedProductDetails.getPrice());
		prevProduct.setQuantity(updatedProductDetails.getQuantity());
		
		Products savedProduct = repo.save(prevProduct);
		
		return getProductResponse(savedProduct);
	}

	@Override
	@Transactional
	public Map<String, Boolean> delete(int id) {
		repo.deleteById(id);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Product deleted", Boolean.TRUE);
		return response;
	}
	
	@Override
	public List<Products> getAllByIdList(List<Integer> ids) {

		List<Products> prodList = repo.findAllById(ids);
		
		return prodList;
	}
	
	private ProductsResponse getProductResponse(Products savedProducts){
		
		return new ProductsResponse(
				savedProducts.getId(),
				savedProducts.getName(),
				savedProducts.getCategory(),
				savedProducts.getPrice(),
				savedProducts.getQuantity()
				);
		
	}
	
	private Products getProductFromRequest(ProductsRequest productRequest) {
		
		return new Products(
				productRequest.getName(),
				productRequest.getCategory(),
				productRequest.getPrice(),
				productRequest.getQuantity()
				);
	}

}
