package com.api.shopproductsservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.shopproductsservice.entity.Products;
import com.api.shopproductsservice.request.ProductsRequest;
import com.api.shopproductsservice.response.ProductsResponse;
import com.api.shopproductsservice.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	@Autowired
	private ProductsService service;
	
	@PostMapping("/create")
	public ProductsResponse create(@RequestBody ProductsRequest prodcutRequest) {
		return service.create(prodcutRequest);
	}
	
	@GetMapping("/getbyid/{id}")
	public ProductsResponse getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@GetMapping("/getall")
	public List<Products> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/update/{id}")
	public ProductsResponse update(@PathVariable int id, @RequestBody Products updatedProductDetails) {
		return service.update(id, updatedProductDetails);
	}
	
	@GetMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable int id) {
		return service.delete(id);
	}
	
	@PostMapping("/getallbyids")
	public List<Products> getAllByIds(@RequestBody List<Integer> prodIds) {
		return service.getAllByIdList(prodIds);
	}
	
}
