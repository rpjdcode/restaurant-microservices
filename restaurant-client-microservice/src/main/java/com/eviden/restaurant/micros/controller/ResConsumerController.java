package com.eviden.restaurant.micros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.restaurant.micros.dto.ResConsumerDTO;
import com.eviden.restaurant.micros.entity.ResConsumer;
import com.eviden.restaurant.micros.model.Booking;
import com.eviden.restaurant.micros.service.ResConsumerService;

@RestController
@RequestMapping("/api/consumers")
public class ResConsumerController {

	@Autowired
	private ResConsumerService service;
	
	@GetMapping
	public ResponseEntity<?> rootEndpoint() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/booking/{consumerId}")
	public ResponseEntity<?> getConsumerBookingsEndpoint(@PathVariable("consumerId")long consumerId) {
		return ResponseEntity.ok(service.findConsumerBookings(consumerId));
	}
	
	@GetMapping("/booking/feign/{consumerId}")
	public ResponseEntity<?> getConsumerBookingsFeignEndpoint(@PathVariable("consumerId")long consumerId) {
		return ResponseEntity.ok(service.feignFindConsumerBookings(consumerId));
	}
	
	@PostMapping("/booking/feign/add")
	public ResponseEntity<?> addConsumerBookingFeignEndpoint(@RequestBody Booking booking) {
		Booking inserted = service.saveBooking(booking);
		
		if (inserted != null) {
			return ResponseEntity.ok(inserted);
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> insertConsumerEndpoint(@RequestBody ResConsumerDTO data) {
		return ResponseEntity.ok(service.saveAndFlush(new ResConsumer(data)));
	}
}
