package com.project.springSecurityJwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.springSecurityJwt.models.AuthenticationRequest;
import com.project.springSecurityJwt.models.AuthenticationRespons;

@Controller
public class HelloResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private jwtUtil jwtTokenUtil;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/authenticate", method= RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
	}catch (BadCredentialsException e) {
		throw new Exception("InCorrect Username and Password", e);
	}
		final UserDetails userDetails= userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt= jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationRespons(jwt));
}
	
}
