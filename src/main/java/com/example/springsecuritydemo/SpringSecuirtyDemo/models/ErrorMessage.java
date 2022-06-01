package com.example.springsecuritydemo.SpringSecuirtyDemo.models;

public class ErrorMessage {
	private String error;
	
	public ErrorMessage(String error) {
		this.error = error;
	}
	
	public String getError() {
		return this.error;
	}
	
}
