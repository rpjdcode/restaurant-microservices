package com.eviden.restaurant.micros.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class CustomJwt extends JwtAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String username;
	private String firstName;
	private String lastName;

	public CustomJwt(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
		super(jwt, authorities);

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
