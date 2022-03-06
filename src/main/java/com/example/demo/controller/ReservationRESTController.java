package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Reservation;
import com.example.demo.repositories.IReservationRepository;
//import com.example.demo.repositories.ReservationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReservationRESTController
{
	// FIELDS
	Logger logger = LoggerFactory.getLogger(ReservationRESTController.class);
	private final IReservationRepository reservationRepo;

	public ReservationRESTController(IReservationRepository reservationRepo)
	{
		super();
		this.reservationRepo = reservationRepo;
	}
	
	@GetMapping("/reservations")
	Flux<Reservation> retrieveAllReservations()
	{
		//return reservationRepo.retrieveAllReservations();
		logger.info("retrieveAllReservations() called");
		return reservationRepo.findAll();
	}
	
	@PostMapping("")
	Mono<Reservation> createReservationFor()
	{
		//return reservationRepo.retrieveAllReservations();
		logger.info("createReservationFor() called");
		return null;
	}
}
