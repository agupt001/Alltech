package com.api.shopuserservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private int id;
	private String name;
	private String email;
	private Long phone;
	private String username;
	private String password;
	
}
