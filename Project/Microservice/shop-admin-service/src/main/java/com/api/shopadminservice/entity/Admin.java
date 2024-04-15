package com.api.shopadminservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String email;
	private Long phone;
	private String username;
	private String password;
	
	public Admin(String name, String email, Long phone, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}
	
}
