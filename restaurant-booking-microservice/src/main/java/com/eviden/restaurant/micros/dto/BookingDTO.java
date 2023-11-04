package com.eviden.restaurant.micros.dto;

import java.time.LocalDateTime;

import com.eviden.restaurant.micros.entity.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
	
	private LocalDateTime bookingTime;
	private Long customerId;
	private Long tableId;
	private String additionalComments;

	public BookingDTO(Booking booking) {
		
		this.bookingTime = booking.getBookingTime();
		this.customerId = booking.getCustomerId();
		this.tableId = booking.getCustomerId();
		this.additionalComments = booking.getAdditionalComments();
		
	}
}
