package com.eviden.restaurant.micros.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

	private Long customerId;
	private LocalDateTime bookingTime;
	private Long tableId;
	private String additionalComments;
	
	
}
