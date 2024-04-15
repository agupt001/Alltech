package com.api.shopdiscountsservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discounts")
@Data
@NoArgsConstructor
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private int product_id;
	private int discount_price;
	
	public Discount(int userId, int product_id, int discount_price) {
		super();
		this.userId = userId;
		this.product_id = product_id;
		this.discount_price = discount_price;
	}
}
