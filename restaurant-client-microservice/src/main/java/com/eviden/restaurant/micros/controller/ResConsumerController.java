package com.eviden.restaurant.micros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.restaurant.micros.dto.ResConsumerDTO;
import com.eviden.restaurant.micros.entity.ResConsumer;
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
	
	@GetMapping("/booking/{id}")
	public ResponseEntity<?> getConsumerBookingsEndpoint(@PathVariable("id")long id) {
		return ResponseEntity.ok(service.findConsumerBookings(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> insertConsumerEndpoint(@RequestBody ResConsumerDTO data) {
		return ResponseEntity.ok(service.saveAndFlush(new ResConsumer(data)));
	}
}
