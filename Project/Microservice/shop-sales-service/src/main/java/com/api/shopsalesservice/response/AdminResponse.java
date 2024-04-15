package com.api.shopsalesservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {

	private ProductResponse product;
	private SalesResponse sales;
}
