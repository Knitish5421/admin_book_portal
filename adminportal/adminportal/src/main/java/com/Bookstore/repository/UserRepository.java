package com.Bookstore.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bookstore.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	User findByEmail(String email);

}
