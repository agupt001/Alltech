package com.api.shopdiscountsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.shopdiscountsservice.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
	List<Discount> findByUserId(int userId);
}
