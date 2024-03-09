package com.api.servicecustomer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

	private int id;
	private String name;
	private String email;
	private Long phone;
	private OrderResponse orderResponse;
	
}
