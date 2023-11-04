package com.eviden.restaurant.micros.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.eviden.restaurant.micros.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String userName;
	private String email;
	private LocalDate birthdayDate;
	private LocalDateTime joinDate;
	private String avatar;
	
	public UserDTO(User user) {
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.birthdayDate = user.getBirthdayDate();
		this.joinDate = user.getJoinDate();
		this.avatar = user.getAvatar();
	}
}
