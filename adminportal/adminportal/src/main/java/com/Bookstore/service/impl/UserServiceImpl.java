package com.Bookstore.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Bookstore.model.Role;
import com.Bookstore.model.User;
import com.Bookstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	// private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository					userRepository;

	@Autowired
	private BCryptPasswordEncoder			passwordEncoder;



	@Override
	public User findByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepository.findByUsername(username);
		if (user == null)
		{
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
	{
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	


	/*
	 * public User CreateNewUser(String userEmail, String username, String encryptedPassword)
	 * {
	 * User user = new User();
	 * user.setUsername(username);
	 * user.setEmail(userEmail);
	 * user.setPassword(encryptedPassword);
	 * user.setRoles(Arrays.asList(new Role("ROLE_USER")));
	 * return userRepository.save(user);
	 * }
	 */

	
}
	
