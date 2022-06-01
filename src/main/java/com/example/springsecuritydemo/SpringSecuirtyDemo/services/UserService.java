package com.example.springsecuritydemo.SpringSecuirtyDemo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springsecuritydemo.SpringSecuirtyDemo.models.User;

public interface UserService extends UserDetailsService{
	User addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(long id);
	
	List<User> allUsers();
}
