package com.api.shopadminservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {

	private String name;
	private String email;
	private Long phone;
	private String username;
	private String password;
	
}
