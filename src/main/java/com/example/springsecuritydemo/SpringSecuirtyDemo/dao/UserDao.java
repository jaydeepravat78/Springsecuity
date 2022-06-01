package com.example.springsecuritydemo.SpringSecuirtyDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecuritydemo.SpringSecuirtyDemo.models.User;

public interface UserDao extends JpaRepository<User, Long>{
	User findDistinctByName(String name);
}
