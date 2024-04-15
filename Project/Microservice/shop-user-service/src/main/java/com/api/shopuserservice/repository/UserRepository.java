package com.api.shopuserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.shopuserservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
