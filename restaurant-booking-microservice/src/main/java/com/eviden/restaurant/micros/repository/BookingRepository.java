package com.eviden.restaurant.micros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eviden.restaurant.micros.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Long>{

	List<Booking> findByTableId(Long tableId);
	
	List<Booking> findByClientId(Long clientId);
	
	@Query("SELECT bo FROM Booking bo WHERE (bo.bookingTime BETWEEN :dateTime AND :bookingTimeLimit) AND bo.clientId=:clientId")
	List<Booking> findByClientDateRange(@Param("client")Long clientId, @Param("dateTime")LocalDateTime dateTime, @Param("bookingTimeLimit")LocalDateTime limit);
}
