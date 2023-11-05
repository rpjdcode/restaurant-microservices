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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/consumers")
public class ResConsumerController {

	@Autowired
	private ResConsumerService service;
	
	@GetMapping
	public ResponseEntity<?> rootEndpoint() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@CircuitBreaker(name = "bookingsCB", fallbackMethod = "fallbackGetConsumerBookings")
	@GetMapping("/booking/{consumerId}")
	public ResponseEntity<?> getConsumerBookingsEndpoint(@PathVariable("consumerId")long consumerId) {
		return ResponseEntity.ok(service.findConsumerBookings(consumerId));
	}
	
	@CircuitBreaker(name = "bookingsCB", fallbackMethod = "fallbackGetConsumerBookings")
	@GetMapping("/booking/feign/{consumerId}")
	public ResponseEntity<?> getConsumerBookingsFeignEndpoint(@PathVariable("consumerId")long consumerId) {
		return ResponseEntity.ok(service.feignFindConsumerBookings(consumerId));
	}
	
	@CircuitBreaker(name = "bookingsCB", fallbackMethod = "fallbackAddConsumerBooking")
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
	
	/**
	 * Método fallback que será llamado por el circuit breaker en caso de producirse un error
	 * al intentar comunicarse con el microservicio de bookings.
	 * Los métodos fallback deben tener estrictamente los mismos parámetros de entrada y un parámetro extra
	 * que represente el error recibido
	 * @param booking
	 * @param error
	 * @return
	 */
	@SuppressWarnings("unused")
	private ResponseEntity<?> fallbackAddConsumerBooking(@RequestBody Booking booking, Exception error) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo añadir la reserva al cliente. Error: ");
	}
	
	/**
	 * Método fallback que se llamará en los endpoints que obtienen información
	 * del microservicio de bookings
	 * @param consumerId
	 * @param error
	 * @return
	 */
	@SuppressWarnings("unused")
	private ResponseEntity<?> fallbackGetConsumerBookings(@PathVariable("consumerId")long consumerId, Exception error) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener los bookings del consumidor");
	}
}
