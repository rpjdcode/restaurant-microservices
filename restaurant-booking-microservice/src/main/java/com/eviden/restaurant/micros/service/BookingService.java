package com.eviden.restaurant.micros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eviden.restaurant.micros.entity.Booking;
import com.eviden.restaurant.micros.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository repository;
	
	public List<Booking> findAll() {
		return repository.findAll();
	}
	
	public Booking findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Booking> findByClientId(long id) {
		return repository.findByClientId(id);
	}

	@Transactional
	public Booking save(Booking booking) {
		return repository.save(booking);
	}
	
	@Transactional
	public Booking saveAndFlush(Booking booking) {
		return repository.saveAndFlush(booking);
	}
	
	@Transactional
	public boolean delete(Booking booking) {
		boolean ret = false;
		try {
			repository.delete(booking);
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
