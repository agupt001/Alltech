package com.api.shopdiscountsservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {
	
	private int id;
	private ProductResponse product;
	private int discount_price;
	
}
