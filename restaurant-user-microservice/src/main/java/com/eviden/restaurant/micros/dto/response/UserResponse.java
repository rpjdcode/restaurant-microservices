package com.eviden.restaurant.micros.dto.response;

import com.eviden.restaurant.micros.dto.UserDTO;

/**
 * 
 * @author Ruben
 * @see APIResponse
 */
public class UserResponse extends APIResponse{
	
	private UserDTO user;
	
	public UserResponse() {
		super();
	}
	
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public UserDTO getUser() {
		return user;
	}
}
