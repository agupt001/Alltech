package com.api.shopsalesservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesRequest {
	
	private int userId;
	private int product_id;
	private String product_name;
	private String product_category;
	private Integer discount_id;
	private int product_price;
	private Integer discount;
	private int final_price;
	private int quantity;
	
}
