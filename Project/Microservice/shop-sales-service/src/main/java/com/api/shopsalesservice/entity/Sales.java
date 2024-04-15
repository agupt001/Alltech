package com.api.shopsalesservice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id")
	private int userId;
	
	private int product_id;
	private String product_name;
	private String product_category;
	private Integer discount_id = null;
	private int product_price;
	private Integer discount = null;
	private int final_price;
	private int quantity;
	private Date created_date = new Date(System.currentTimeMillis());
	
	public Sales(int userId, int product_id, String product_name, String product_category, int discount_id, 
			int product_price, int discount, int final_price, int quantity) {
		super();
		this.userId = userId;
		this.product_id = product_id;
		this.product_name = product_name;
		this.discount_id = discount_id;
		this.product_price = product_price;
		this.product_category = product_category;
		this.discount = discount;
		this.final_price = final_price;
		this.quantity = quantity;
	}
	
	public Sales(int userId, int product_id, String product_name, String product_category, 
			int product_price, int final_price, int quantity) {
		super();
		this.userId = userId;
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_category = product_category;
		this.product_price = product_price;
		this.final_price = final_price;
		this.quantity = quantity;
	}
	
}
