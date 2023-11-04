package com.eviden.restaurant.micros.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eviden.restaurant.micros.model.Booking;

@FeignClient(name = "restaurant-booking-microservice", url="http://localhost:9004")
public interface BookingFeignClient {

	@GetMapping("/api/bookings/table/{tableId}")
	List<Booking> findTableBookings(@PathVariable("tableId")long tableId);
}
