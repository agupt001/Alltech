package com.api.shopproductsservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsRequest {
	
	private String name;
	private String category;
	private int price;
	private int quantity;
	
}
