package com.project.springSecurityJwt.models;

public class AuthenticationRespons {
	
	private final String jwt;
	
	public AuthenticationRespons(String jwt) {
		this.jwt = jwt;
	}


	public String getJwt() {
		return jwt;
	}
	
	
}
