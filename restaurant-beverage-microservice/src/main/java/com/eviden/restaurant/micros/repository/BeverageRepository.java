package com.eviden.restaurant.micros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eviden.restaurant.micros.entity.Beverage;
import com.eviden.restaurant.micros.entity.BeverageType;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

	@Query("SELECT bev FROM Beverage bev WHERE bev.type = :type")
	List<Beverage> findByType(@Param("type")BeverageType type);
}
