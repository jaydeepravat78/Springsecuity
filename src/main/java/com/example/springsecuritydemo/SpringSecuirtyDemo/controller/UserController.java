package com.example.springsecuritydemo.SpringSecuirtyDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecuritydemo.SpringSecuirtyDemo.models.ErrorMessage;
import com.example.springsecuritydemo.SpringSecuirtyDemo.models.JwtRequest;
import com.example.springsecuritydemo.SpringSecuirtyDemo.models.JwtRespose;
import com.example.springsecuritydemo.SpringSecuirtyDemo.models.User;
import com.example.springsecuritydemo.SpringSecuirtyDemo.services.UserService;
import com.example.springsecuritydemo.SpringSecuirtyDemo.utility.JwtUtil;

@RestController
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	User signIn(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.addUser(user);
	}
	
	@PutMapping("/update/{id}")
	String updateuser(@RequestBody User user, @PathVariable long id) {
		user.setId(id);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.updateUser(user);
		return "updated Successfully";
	}
	
	@PostMapping("/delete/{id}")
	String deleteuser(@PathVariable long id) {
		userService.deleteUser(id);
		return "User deleted successfully";
	}
	
	@GetMapping("/showusers")
	List<User> allUsers() {
		return userService.allUsers();
	}
	
	@PostMapping("/signin")
	ResponseEntity<?> signIn(@RequestBody JwtRequest request) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		}
		catch(BadCredentialsException e) {
			return ResponseEntity.ok(new ErrorMessage("User not found!" + e));
		}
		final UserDetails userDetails = userService.loadUserByUsername(request.getUserName());
		final String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtRespose(token));
	}
	
	@GetMapping("/")
	String defaultUrl() {
		return "default Url";
	}
	
	
}
