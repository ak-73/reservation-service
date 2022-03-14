package com.example.demo.service;

import java.security.SecureRandom;
import java.util.Comparator;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.dtos.ReservationDTO;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.repositories.IHotelRepository;
import com.example.demo.repositories.IReservationRepository;

import reactor.core.publisher.Mono;

@Component
public class ReservationService
{
	// FIELDS
	private final IReservationRepository reservationRepo;
	private final IHotelRepository hotelRepository;
	
	
	// CONSTRUCTORS
	public ReservationService(IReservationRepository reservationRepo, IHotelRepository hotelRepository)
	{
		super();
		this.reservationRepo = reservationRepo;
		this.hotelRepository = hotelRepository;
	}
	
	
	// MAIN
	public Mono<ServerResponse> retrieveReservationByID(ServerRequest request)
	{
		int id = Integer.valueOf(request.pathVariable("id"));
		Mono<Reservation> reservation = reservationRepo.findById(id);
		return ServerResponse.ok().body(reservation, Reservation.class);
	}
	
	
	// we have two versions of this method: async and sync (the latter gets used for
	// synchronous population of the Mongo DB
	public Mono<Reservation> findSuitableLodgingsReactively(ReservationDTO reservationDTO)
	{
		SecureRandom secureRandom = new SecureRandom();
		
		// this is the reactive version of the imperative code in
		// findSuitableLodgingsSynchronously() below
		// this needs to be revised, it can probably be done better
		Mono<Reservation> reservationMono = hotelRepository.count().map(hotelCount -> secureRandom.nextInt(1, hotelCount.intValue() + 1))
				.filter(hotelCount -> hotelCount != null).flatMap(randomHotelIndex -> hotelRepository.findById(randomHotelIndex))
				.flatMap(availableHotel -> reservationRepo.findAll().sort(Comparator.comparing(Reservation::getId).reversed()).next()
						.<Reservation>handle((reservation, sink) ->
						{
							Reservation r = new Reservation(reservation.getId() + 1, reservationDTO.getTitle(), reservationDTO.getFirstName(),
									reservationDTO.getLastName(),
									// TODO skip room no. 13
									availableHotel, secureRandom.nextInt(1000), reservationDTO.getCheckInDate(), reservationDTO.getCheckOutDate());
							sink.next(r);
						}))
				.log().flatMap(reservation -> reservationRepo.save(reservation));
		return reservationMono;
		
		/*
		//well, that didnt work
		//TODO remove in the next commit
		//@formatter:off
		return hotelRepository.count()
				.map(hotelCount -> secureRandom.nextInt(1, hotelCount.intValue() +1))
				.doOnEach(hotelCount -> System.out.println("hotelCount[in Service class]: " + hotelCount.get()))
				.flatMap(randomHotelIndex -> hotelRepository.findById(randomHotelIndex))
				.doOnEach(hotel -> System.out.println("hotel[in Service class]: " + hotel.get()))
				.map(availableHotel ->  Pair.of(availableHotel, reservationRepo.findAll().sort(Comparator.comparing(Reservation::getId).reversed()).next()))
				.filter(pair -> pair.getFirst() != null && pair.getSecond() != null)
				.doOnEach(pair -> System.out.println("pair:availableHotel[in Service class]: " + pair.get()))
				//.doOnEach(pair -> System.out.println("pair:availableHotel[in Service class]: " + pair.get().getFirst()))
				//.doOnEach(pair -> System.out.println("pair:reservationMono[in Service class]: " + pair.get().getSecond()))
				//.flatMap(availableHotel ->  Mono.just(Pair.of(availableHotel, reservationRepo.findAll().sort(Comparator.comparing(Reservation::getId).reversed()).next())))
				//.map(pair -> pair.getSecond().map(reservation -> new Reservation()))
				.log()
				.flatMap(pair -> pair.getSecond().map(reservation -> new Reservation(reservation.getId()+1, reservationDTO.getTitle(), reservationDTO.getFirstName(), reservationDTO.getLastName(), pair.getFirst(), secureRandom.nextInt(1000), reservationDTO.getCheckInDate(), reservationDTO.getCheckOutDate())))
				.doOnEach(reservation -> System.out.println("New Reservation[in Service class]: " + reservation.get())) //useful for looking into a stream
				//.map(reservation -> Pair.of(reservation, 14))
				//.map(pair -> pair.getFirst())
				;
			//@formatter:off
			*/
		
		
	}
	
	// we can call this safely from the Initializer class, where entries need to be
	// built sequentially aka synchronously
	public Mono<Reservation> findSuitableLodgingsSynchronously(ReservationDTO reservationDTO)
	{
		SecureRandom secureRandom = new SecureRandom();
		
		// determine hotel
		Long result = hotelRepository.count().block(); // given the small test DB, we can afford to block here.
		int hotelCount = result != null ? result.intValue() : 0;
		int randomHotelIndex = secureRandom.nextInt(1, hotelCount + 1);
		Hotel availableHotel = hotelRepository.findById(randomHotelIndex).block(); // again, we can afford it here
		
		// determine reservationID
		Reservation reservationWithHighestId = reservationRepo.findAll().sort(Comparator.comparing(Reservation::getId).reversed()).blockFirst();
		int reservationMaxId = reservationWithHighestId != null ? reservationWithHighestId.getId() : 0;
		int newReservationId = reservationMaxId + 1;
		
		//determine random room no.
		int randomRoomNo = 7;
		do
		{
			randomRoomNo = secureRandom.nextInt(1000);
		}
		while (randomRoomNo == 13); // no room no.13 in hotels!
		
		//create new reservation and return as a Mono
		Reservation reservation = new Reservation(newReservationId, reservationDTO.getTitle(), reservationDTO.getFirstName(), reservationDTO.getLastName(),
				availableHotel, randomRoomNo, reservationDTO.getCheckInDate(), reservationDTO.getCheckOutDate());
		return Mono.just(reservation);
	}
	
	
}
