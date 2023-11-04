package com.eviden.restaurant.micros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eviden.restaurant.micros.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	// Agregar métodos personalizados de consultas
	public User findByUserName(String username);
	
	public User findByEmail(String email);
}
