package com.api.shopadminservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {

	private int id;
	private String name;
	private String email;
	private Long phone;
	private String username;
	private String password;
	
}
