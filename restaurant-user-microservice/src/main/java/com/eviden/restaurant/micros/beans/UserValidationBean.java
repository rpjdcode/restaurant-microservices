package com.eviden.restaurant.micros.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eviden.restaurant.micros.config.ResponsesPropertiesConfig;
import com.eviden.restaurant.micros.dto.UserDTO;
import com.eviden.restaurant.micros.dto.request.UpdateUserRequest;
import com.eviden.restaurant.micros.entity.User;
import com.eviden.restaurant.micros.service.UserService;
import com.eviden.restaurant.micros.util.RegExpPatterns;
import com.eviden.restaurant.micros.util.UserValidationType;

/**
 * Bean encargada de obtener el listado de errores total
 * de una validación de datos de usuario
 * @author Ruben
 *
 */
@Component
public class UserValidationBean {

	/**
	 * Clase de configuración con las propiedades del archivo responses.properties
	 */
	@Autowired
	private ResponsesPropertiesConfig messagesConfig;
	
	/**
	 * 
	 */
	@Autowired
	private RegExpPatterns patterns;
	
	/**
	 * Método encargado de validar los datos provenientes de una petición
	 * relacionada con los usuarios
	 * @param request Petición con los datos de usuario
	 * @return
	 */
	public List<String> validateUserData(UserService service, UserDTO data, UserValidationType type) {
		
		List<String> errors = new ArrayList<String>();
		String username = data.getUserName();
		String email = data.getEmail();
		
		// Validación de nombre de usuario
		if (username == null) {
			errors.add(messagesConfig.getUsernameValidationNullMessage());
		} else {
			if (username.isEmpty() || username.isBlank()) {
				errors.add(messagesConfig.getUsernameValidationEmptyMessage());
			} else {
				if (!patterns.validateUsername(username)) {
					errors.add(messagesConfig.getUsernameValidationFormatMessage());
				} else {
					User registered = service.findByUserName(username);
					
					if (registered != null && type == UserValidationType.INSERT) {
						errors.add(messagesConfig.getUserExistingMsg());
					}
				}
			}
			

		}
		
		if (email == null) {
			errors.add(messagesConfig.getEmailValidationNullMessage());
		} else {
			if (email.isEmpty() || email.isBlank()) {
				errors.add(messagesConfig.getEmailValidationEmptyMessage());
			} else {
				if (!patterns.validateEmail(email)) {
					errors.add(messagesConfig.getEmailValidationFormatMessage());
				}
				
				User registered = service.findByEmail(email);
				
				if (registered != null && type == UserValidationType.INSERT) {
					errors.add(messagesConfig.getUserEmailExistingMsg());
				}
			}
			
			
		}
		
		return errors;
	}
	
	/**
	 * Método encargado de validar que los datos que se proporcionan durante
	 * una actualización de datos de usuario son correctos.
	 * @return
	 */
	public List<String> validateUserUpdateData(UserService service, UpdateUserRequest request) {
		
		List<String> errors = new ArrayList<String>();
		UserDTO data = request.getData();
		
		if (data == null) {
			errors.add(messagesConfig.getUserUpdateDataNullMessage());
		} else {
			Long id = request.getId();
			if (id == null) {
				errors.add(messagesConfig.getUserUpdateIdentifierNullMessage());
			} else {
				
				User original = service.findById(id);
				
				if (original == null) {
					errors.add(messagesConfig.getUserUpdateIdentifierNotFoundMessage());
				}
			}
			errors.addAll(validateUserData(service, data, UserValidationType.UPDATE));
		}
		
		return errors;
	}
}
