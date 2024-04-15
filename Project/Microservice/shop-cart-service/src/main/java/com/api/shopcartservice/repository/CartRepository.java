package com.api.shopcartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.shopcartservice.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Cart> findByUserId(int userId);
}
