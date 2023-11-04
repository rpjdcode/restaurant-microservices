package com.eviden.restaurant.micros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.eviden.restaurant.micros.config.RestTemplateConfig;
import com.eviden.restaurant.micros.entity.ResConsumer;
import com.eviden.restaurant.micros.feign.BookingFeignClient;
import com.eviden.restaurant.micros.model.Booking;
import com.eviden.restaurant.micros.repository.ResConsumerRepository;

@Service
public class ResConsumerService {

	@Autowired
	private RestTemplateConfig restTemplateConfig;

	@Autowired
	private ResConsumerRepository repository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BookingFeignClient bookingFeignClient;

	public List<ResConsumer> findAll() {
		return repository.findAll();
	}

	/**
	 * Método encargado de encontrar las reservas relacionadas con un cliente, estableciendo
	 * la comunicación con RestTemplate hacia un endpoint del microservicio de bookings
	 * @param consumerId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Booking> findConsumerBookings(long consumerId) {
		String url = restTemplateConfig.getBookingsBaseUrl().concat(restTemplateConfig.getBookingsPort())
				.concat(restTemplateConfig.getBookingsEndpoint()+"/booking/");
		List<Booking> bookings = restTemplate.getForObject(url + consumerId,
				List.class);
		return bookings;
	}
	
	/**
	 * Obtiene las reservas de un cliente usando FeignClient como método de comunicación
	 * con el microservicio de reservaws
	 * @param consumerId
	 * @return Lista de reservas del cliente
	 */
	public List<Booking> feignFindConsumerBookings(long consumerId) {
		return bookingFeignClient.findConsumerBookings(consumerId);
	}

	public ResConsumer findById(long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	public ResConsumer save(ResConsumer consumer) {
		return repository.save(consumer);
	}

	@Transactional
	public ResConsumer saveAndFlush(ResConsumer consumer) {
		return repository.saveAndFlush(consumer);
	}
	
	@Transactional
	public Booking saveBooking(Booking booking) {
		return bookingFeignClient.save(booking);
	}

	@Transactional
	public boolean delete(ResConsumer consumer) {
		boolean ret = false;
		try {
			repository.delete(consumer);
			ret = true;
		} catch (Exception error) {
			error.printStackTrace();
		}
		return ret;
	}

	@Transactional
	public boolean deleteById(long id) {
		boolean ret = false;
		try {
			repository.deleteById(id);
			ret = true;
		} catch (Exception error) {
			error.printStackTrace();
		}
		return ret;
	}
}
