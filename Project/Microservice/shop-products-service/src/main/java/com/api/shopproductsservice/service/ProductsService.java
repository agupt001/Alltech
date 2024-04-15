package com.api.shopproductsservice.service;

import java.util.List;
import java.util.Map;

import com.api.shopproductsservice.entity.Products;
import com.api.shopproductsservice.request.ProductsRequest;
import com.api.shopproductsservice.response.ProductsResponse;

public interface ProductsService {
	
	ProductsResponse create(ProductsRequest productRequest);
	ProductsResponse getById(int id);
	List<Products> getAll();
	ProductsResponse update(int id, Products updatedProductDetails);
	Map<String, Boolean> delete(int id);
	List<Products> getAllByIdList(List<Integer> ids);
	
}
