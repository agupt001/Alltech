package com.api.shopwishlistservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.shopwishlistservice.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	List<Wishlist> findByUserId(int userId);
}
