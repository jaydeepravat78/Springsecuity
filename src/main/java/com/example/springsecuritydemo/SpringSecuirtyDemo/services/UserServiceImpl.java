package com.example.springsecuritydemo.SpringSecuirtyDemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecuritydemo.SpringSecuirtyDemo.dao.UserDao;
import com.example.springsecuritydemo.SpringSecuirtyDemo.models.CustomUserDetails;
import com.example.springsecuritydemo.SpringSecuirtyDemo.models.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao dao;
	
	
	
	@Override
	public User addUser(User user) {
		return dao.save(user);
	}



	@Override
	public void updateUser(User user) {
		dao.save(user);
	}



	@Override
	public void deleteUser(long id) {
		dao.deleteById(id);
	}



	@Override
	public List<User> allUsers() {
		return dao.findAll();
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.findDistinctByName(username);
		if(user == null)
			throw new UsernameNotFoundException("User not found");
		return new CustomUserDetails(user);
	}

}
