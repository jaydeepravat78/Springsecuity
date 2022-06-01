package com.example.springsecuritydemo.SpringSecuirtyDemo.models;

public class JwtRespose {
	String token;

	public JwtRespose(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}
