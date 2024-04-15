package com.api.shopauthservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.shopauthservice.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {

	Optional<UserCredential> findByUsername(String username);
}
