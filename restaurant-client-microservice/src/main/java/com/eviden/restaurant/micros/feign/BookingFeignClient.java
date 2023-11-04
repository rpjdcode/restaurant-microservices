package com.eviden.restaurant.micros.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eviden.restaurant.micros.model.Booking;

@FeignClient(name = "restaurant-booking-microservice")
public interface BookingFeignClient  {
	
	@PostMapping("/api/bookings/add")
	Booking save(@RequestBody Booking booking); 
	
	@GetMapping("/api/bookings/booking/{consumerId}")
	List<Booking> findConsumerBookings(@PathVariable("consumerId")long consumerId);
	
}
