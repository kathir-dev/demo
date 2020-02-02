package com.coding.app.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.app.demo.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByUsername(String userName);
}
