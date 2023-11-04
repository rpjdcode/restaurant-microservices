package com.eviden.restaurant.micros.entity;

import java.time.LocalDateTime;

import com.eviden.restaurant.micros.dto.BookingDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(11)", length = 11, nullable = false)
	private Long id;
	
	@Column(name = "booking_time", columnDefinition = "DATETIME", nullable = false)
	private LocalDateTime bookingTime;
	
	@Column(name = "client_id", columnDefinition = "INT(11)", nullable = false)
	private Long clientId;
	
	@Column(name = "tableId", columnDefinition = "INT(11)", nullable = false)
	private Long tableId;
	
	@Column(name = "additional_comments", columnDefinition = "MEDIUMTEXT", nullable = true)
	private String additionalComments;
	
	public Booking(BookingDTO dto) {
		this.bookingTime = dto.getBookingTime();
		this.clientId = dto.getClientId();
		this.tableId = dto.getTableId();
		this.additionalComments = dto.getAdditionalComments();
	}
}
