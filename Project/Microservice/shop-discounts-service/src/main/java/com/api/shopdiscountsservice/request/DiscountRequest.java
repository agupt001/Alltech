package com.api.shopdiscountsservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRequest {
	private int userId;
	private int product_id;
	private int discount_price;
}
