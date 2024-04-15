package com.api.shopadminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.shopadminservice.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
