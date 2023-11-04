package com.eviden.restaurant.micros.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una respuesta de la API
 * @author Ruben
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {

	private LocalDateTime responseDate = LocalDateTime.now();
	private List<String> errors;
	private String message;
}
