package com.eviden.restaurant.micros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eviden.restaurant.micros.entity.Beverage;
import com.eviden.restaurant.micros.entity.BeverageType;
import com.eviden.restaurant.micros.repository.BeverageRepository;

@Service
public class BeverageService {

	@Autowired
	private BeverageRepository repository;
	
	public List<Beverage> findAll() {
		return repository.findAll();
	}
	
	public Beverage findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Beverage> findByType(BeverageType type) {
		return repository.findByType(type);
	}
	
	public Beverage save(Beverage beverage) {
		return repository.save(beverage);
	}
	
	public Beverage saveAndFlush(Beverage beverage) {
		return repository.saveAndFlush(beverage);
	}
	
	public void delete(Beverage beverage) {
		repository.delete(beverage);
	}
	
	public void deleteById(long id) {
		repository.deleteById(id);
	}
}
