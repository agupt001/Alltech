package com.api.shopcartservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

	private int userId;
	private int product_id;
	private int quantity;
	
}
