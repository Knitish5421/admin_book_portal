package com.Bookstore.service.impl;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.Bookstore.model.User;

public interface UserService extends UserDetailsService {

	User findByUsername(String username);
	
}
