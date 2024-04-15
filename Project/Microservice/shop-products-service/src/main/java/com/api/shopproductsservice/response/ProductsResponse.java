package com.api.shopproductsservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponse {
	private int id;
	private String name;
	private String category;
	private int price;
	private int quantity;
}
