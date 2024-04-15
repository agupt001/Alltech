package com.api.shopsalesservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.shopsalesservice.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
	List<Sales> findByUserId(int userId);
}
