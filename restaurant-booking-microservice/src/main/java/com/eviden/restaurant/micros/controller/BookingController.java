package com.eviden.restaurant.micros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.restaurant.micros.dto.BookingDTO;
import com.eviden.restaurant.micros.entity.Booking;
import com.eviden.restaurant.micros.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService service;
	
	@GetMapping
	public ResponseEntity<?> rootEndpoint() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/booking/{customerId}")
	public ResponseEntity<?> getBookingByCustomerIdEndpoint(@PathVariable("customerId")long customerId) {
		return ResponseEntity.ok(service.findByCustomerId(customerId));
	}
	
	@GetMapping("/table/{tableId}")
	public ResponseEntity<?> getBookingByTableIdEndpoint(@PathVariable("tableId")long tableId) {
		return ResponseEntity.ok(service.findByTableId(tableId));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> insertBookingEndpoint(@RequestBody BookingDTO data) {
		
		Booking inserted = service.saveAndFlush(new Booking(data));
		
		return ResponseEntity.status(HttpStatus.OK).body(inserted);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateBookingEndpoint() {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteBookingEndpoint() {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
