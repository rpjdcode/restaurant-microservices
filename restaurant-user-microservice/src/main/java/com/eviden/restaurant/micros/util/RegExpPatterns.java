package com.eviden.restaurant.micros.util;

import java.util.regex.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Clase encargada de definir las expresiones regulares de validación de campos
 * de usuario
 * @author Ruben
 *
 */
@Data
@NoArgsConstructor
public class RegExpPatterns {

	private final String emailFormat = "^[A-Za-z0-9+_.-]+@(.+)$";
	private final String usernameFormat = "^[A-Za-z0-9_]{6,25}$";
	
	public boolean validateEmail(String email) {
		return Pattern.matches(emailFormat, email);
	}
	
	public boolean validateUsername(String username) {
		return Pattern.matches(usernameFormat, username);
	}
	
}
