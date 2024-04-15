package com.api.shopcartservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping_cart")
@Data
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private int product_id;
	private int quantity;
	
	public Cart(int userId, int product_id, int quantity) {
		super();
		this.userId = userId;
		this.product_id = product_id;
		this.quantity = quantity;
	}
	
}
