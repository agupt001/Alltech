package com.api.shopsalesservice.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesResponse {
	
	private int id;
	private int user_id;
	private int product_id;
	private String product_name;
	private String product_category;
	private int discount_id;
	private int product_price;
	private int discount;
	private int final_price;
	private int quantity;
	private Date created_date;
	
	public SalesResponse(int id, int user_id, int product_id, String product_name, String product_category,
			int product_price, int final_price, int quantity, Date created_date) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_category = product_category;
		this.product_price = product_price;
		this.final_price = final_price;
		this.quantity = quantity;
		this.created_date = created_date;
	}
	
}