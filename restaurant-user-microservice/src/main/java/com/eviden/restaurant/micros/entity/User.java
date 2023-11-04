package com.eviden.restaurant.micros.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.eviden.restaurant.micros.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(11)", nullable = false, length = 11)
	private Long id;
	
	@Column(name ="username", columnDefinition = "VARCHAR(25)", nullable = false, unique = true)
	private String userName;
	
	@Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
	private String email;
	
	@Column(name = "birthday_date", columnDefinition = "DATE", nullable = true)
	private LocalDate birthdayDate;
	
	@Column(name = "join_date", columnDefinition = "DATETIME", nullable = false)
	private LocalDateTime joinDate;
	
	@Column(name = "avatar", columnDefinition = "VARCHAR(255)", nullable = true)
	private String avatar;
	
	/**
	 * Constructor que permite construir un objeto con una request.
	 * Siempre se deben validar las request para construir objetos User válidos
	 * @param request Petición de usuario
	 */
	public User(UserDTO data) {
		userName = data.getUserName();
		email = data.getEmail();
		birthdayDate = data.getBirthdayDate();
		avatar = data.getAvatar();
		joinDate = LocalDateTime.now();
		
	}
	
	
}
