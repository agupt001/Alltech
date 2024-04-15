package com.api.shopauthservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.api.shopauthservice.entity.CustomUserDetails;
import com.api.shopauthservice.entity.UserCredential;
import com.api.shopauthservice.repository.UserCredentialRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserCredentialRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserCredential> credential = repo.findByUsername(username);
		
		return credential.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("user not found with name: "+username));
	}

}
