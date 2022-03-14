package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ReservationDTO;
import com.example.demo.model.Reservation;
import com.example.demo.repositories.IReservationRepository;
import com.example.demo.service.ReservationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReservationRESTController
{
	// FIELDS
	Logger logger = LoggerFactory.getLogger(ReservationRESTController.class);
	private final IReservationRepository reservationRepo;
	private final ReservationService reservationService;
	
	
	// CONSTRUCTORS
	public ReservationRESTController(IReservationRepository reservationRepo, ReservationService reservationService)
	{
		super();
		this.reservationRepo = reservationRepo;
		this.reservationService = reservationService;
	}
	
	// ENDPOINTS
	@GetMapping("/reservations")
	Flux<Reservation> retrieveAllReservations()
	{
		logger.info("retrieveAllReservations() called");
		return reservationRepo.findAll();
	}
	
	@PostMapping("/book_room")
	Mono<Reservation> createReservationFor(@RequestBody ReservationDTO reservation)
	{
		logger.info("createReservationFor() called");
		return reservationService.findSuitableLodgingsReactively(reservation);
	}


}
