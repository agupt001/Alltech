package com.api.serviceorder.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

	private int id;
	private String orderName;
	private int orderPrice;
	
}
