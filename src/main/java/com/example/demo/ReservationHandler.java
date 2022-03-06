package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.model.Reservation;
import com.example.demo.repositories.IReservationRepository;

import reactor.core.publisher.Mono;

@Component
class ReservationHandler
{
	private final IReservationRepository reservationRepo;
	
	public ReservationHandler(IReservationRepository reservationRepo)
	{
		super();
		this.reservationRepo = reservationRepo;
	}

	public Mono<ServerResponse> retrieveReservationByID(ServerRequest request)
	{
		int id = Integer.valueOf(request.pathVariable("id"));
		Mono<Reservation> reservation = reservationRepo.findById(id);
		return ServerResponse.ok().body(reservation, Reservation.class);
	}
	
}