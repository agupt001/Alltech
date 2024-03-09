package com.api.serviceorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.serviceorder.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
