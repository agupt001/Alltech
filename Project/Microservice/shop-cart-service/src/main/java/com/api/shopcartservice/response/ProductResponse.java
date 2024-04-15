package com.api.shopcartservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
	private int id;
	private String name;
	private String category;
	private int price;
	private int quantity;
}
