package com.api.shopuserservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	private String name;
	private String email;
	private Long phone;
	private String username;
	private String password;
	
}
