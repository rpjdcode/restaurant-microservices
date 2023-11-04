package com.eviden.restaurant.micros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eviden.restaurant.micros.entity.ResTable;
import com.eviden.restaurant.micros.repository.ResTableRepository;


/**
 * Servicio relacionado a las entidades de mesas del restaurante
 * @author Ruben
 *
 */
@Service
public class ResTableService {

	@Autowired
	private ResTableRepository repository;
	
	public List<ResTable> findAll() {
		return repository.findAll();
	}
	
	public ResTable findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<ResTable> findByCapacity(int capacity) {
		return repository.findByCapacity(capacity);
	}
	
	public List<ResTable> findByAvailability(boolean available) {
		return repository.findByAvailable(available);
	}
	
	@Transactional
	public ResTable save(ResTable table) {
		return repository.save(table);
	}
	
	@Transactional
	public ResTable saveAndFlush(ResTable table) {
		return repository.saveAndFlush(table);
	}
	
	@Transactional
	public boolean delete(ResTable table) {
		boolean ret = false;
		try {
			repository.delete(table);
			ret = true;
		} catch (Exception error) {
			error.printStackTrace();
		}
		return ret;
	}
	
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
	
}
