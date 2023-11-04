package com.eviden.restaurant.micros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eviden.restaurant.micros.dto.UserDTO;
import com.eviden.restaurant.micros.entity.User;
import com.eviden.restaurant.micros.repository.UserRepository;

/**
 * Servicio de usuarios que interactúa con el repositorio de base de datos
 * para obtener la información requerida por los endpoints de usuario
 * @author Ruben
 *
 */
@Service
public class UserService {

	/**
	 * Repositorio de usuarios de JPA
	 */
	@Autowired
	private UserRepository repository;
	
	/**
	 * Obtiene la lista de usuarios del repositorio
	 * @return Lista de usuarios almacenados en el repositorio
	 */
	public List<User> getUsers() {
		return repository.findAll();
	}
	
	/**
	 * Busca a un usuario a través de su identificador
	 * @param id Identificador de usuario
	 * @return Objeto usuario si se encuentra, null si no se encuentra
	 */
	public User findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	/**
	 * Busca a un usuario a través de su nombre de usuario
	 * @param username Nombre de usuario
	 * @return Objeto usuario si se encuentra, null si no se encuentra
	 */
	public User findByUserName(String username) {
		return repository.findByUserName(username);
	}
	
	/**
	 * Busca a un usuario a través de su email
	 * @param email Email del usuario
	 * @return Objeto usuario si se encuentra, null si no se encuentra
	 */
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	/**
	 * Inserta una entity de usuario
	 * @param user Entidad de usuario a insertar
	 * @return Objeto usuario procesado tras inserción
	 */
	@Transactional
	public User saveAndFlush(User user) {
		return repository.saveAndFlush(user);
	}
	
	/**
	 * Inserta un usuario de una request
	 * @param request
	 * @return Objeto usuario procesado tras inserción
	 */
	@Transactional
	public User saveAndFlush(UserDTO user) {
		return saveAndFlush(new User(user));
	}
	
	@Transactional
	public boolean delete(User user) {
		boolean ret = false;
		try {
			repository.delete(user);
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * Elimina a un usuario a través de su identificador y devuelve un true si es eliminado,
	 * false si se produce algún error durante la eliminación
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean deleteById(long id) {
		boolean ret = false;
		try {
			repository.deleteById(id);
			ret = true;
		} catch (Exception error) {
			error.printStackTrace();
		}
		return ret;
	}
	
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@Transactional
	public User save(User user) {
		return repository.save(user);
	}
	
	/**
	 * 
	 * @param userDto
	 * @return
	 */
	@Transactional
	public User save(UserDTO userDto) {
		return repository.save(new User(userDto));
	}
}
