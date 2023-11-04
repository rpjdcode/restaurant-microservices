package com.eviden.restaurant.micros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eviden.restaurant.micros.entity.ResTable;

public interface ResTableRepository extends JpaRepository<ResTable, Long> {

	@Query("SELECT tab FROM ResTable tab WHERE tab.capacity=:capacity")
	List<ResTable> findByCapacity(@Param("capacity")int capacity);
	
	List<ResTable> findByAvailable(boolean available);
}
