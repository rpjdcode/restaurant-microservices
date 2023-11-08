package com.eviden.restaurant.micros.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.restaurant.micros.beans.UserValidationBean;
import com.eviden.restaurant.micros.config.ResponsesPropertiesConfig;
import com.eviden.restaurant.micros.dto.UserDTO;
import com.eviden.restaurant.micros.dto.request.UpdateUserRequest;
import com.eviden.restaurant.micros.dto.request.UserRequest;
import com.eviden.restaurant.micros.dto.response.UserResponse;
import com.eviden.restaurant.micros.entity.User;
import com.eviden.restaurant.micros.service.UserService;
import com.eviden.restaurant.micros.util.UserValidationType;

@RestController
@RequestMapping("/api/users")
public class UserController {

	/**
	 * Servicio de usuarios de la API
	 */
	@Autowired
	private UserService service;
	
	@Autowired
	private ResponsesPropertiesConfig messagesConfig;
	
	@Autowired
	private UserValidationBean userValidations;

	
	/**
	 * Endpoint que permite obtener todos los usuarios almacenados
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<UserDTO>> rootEndpoint() {

		List<UserDTO> users = service.getUsers().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	/**
	 * Endpoint que permite obtener un usuario en concreto indicado por su nombre de usuario
	 * como parámetro de petición
	 * @param username
	 * @return
	 */
	@GetMapping("/user")
	public ResponseEntity<?> getUserByUsername(@RequestParam("username") String username) {
		UserResponse response = new UserResponse();
		User user = service.findByUserName(username);
		if (user != null) {
			response.setUser(new UserDTO(user));
			response.setMessage(messagesConfig.getUserFoundMsg());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.setMessage(messagesConfig.getUserNotFoundMsg());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
	}
	
	/**
	 * Endpoint que permite insertar un usuario en base de datos a través de una request
	 * que deberá tener una estructura como la de UserRequest.
	 * Si se produce
	 * @param request
	 * @return
	 * @see UserRequest
	 */
	@PostMapping("/add")
	public ResponseEntity<?> insertUser(@RequestBody UserDTO userData) {
		List<String> errors = new ArrayList<String>();
		if (userData != null) {
			
			// Se validan los datos
			List<String> validations = userValidations.validateUserData(service, userData, UserValidationType.INSERT);
			errors.addAll(validations);
			
			// Se comprueba que no hay errores tras la validación
			if (errors.size() == 0) {
				
				User inserted = service.saveAndFlush(userData);
				
				if (inserted != null) {
					UserResponse response = new UserResponse();
					response.setMessage(messagesConfig.getUserInsertedMsg());
					response.setUser(new UserDTO(inserted));
					return ResponseEntity.status(HttpStatus.CREATED).body(response);
				}
			}
		}
		UserResponse response = new UserResponse();
		response.setMessage(messagesConfig.getUserNotValidatedMsg());
		response.setErrors(errors);
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
	}
	
	/**
	 * @param userData
	 * @return
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestBody UserDTO userData) {
		UserResponse response = new UserResponse();
		User user = service.findByUserName(userData.getUserName());
		
		if (user != null) {
			
			response.setMessage(messagesConfig.getUserDeletedMsg());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		response.setMessage(messagesConfig.getUserDeleteNotFoundMsg());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request) {
		List<String> errors = new ArrayList<String>();
		UserResponse response = new UserResponse();
		
		errors.addAll(userValidations.validateUserUpdateData(service, request));
		
		if (errors.size() == 0) {
			UserDTO userData = request.getData();
			User modified = new User(userData);
			
			modified.setId(request.getId());
			
			User updated = service.saveAndFlush(modified);
			
			response.setMessage(messagesConfig.getUserUpdatedMsg());
			response.setUser(new UserDTO(updated));
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		}
		
		response.setErrors(errors);
		response.setMessage(messagesConfig.getUserUpdateFailMsg());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	

	
}
