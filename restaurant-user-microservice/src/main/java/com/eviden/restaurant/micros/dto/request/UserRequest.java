package com.eviden.restaurant.micros.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la estructura JSON requerida para una request relacionada con los usuarios
 * @author Ruben
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	private String username;
	private String email;
	private LocalDate birthdayDate;
	private String avatar;
	private String role;
}
