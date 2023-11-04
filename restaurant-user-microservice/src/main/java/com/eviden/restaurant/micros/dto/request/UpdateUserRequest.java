package com.eviden.restaurant.micros.dto.request;

import com.eviden.restaurant.micros.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

	private Long id;
	private UserDTO data;
}
