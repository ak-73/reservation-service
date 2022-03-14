package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dtos.ReservationDTO;
import com.example.demo.model.Address;
import com.example.demo.model.Hotel;
import com.example.demo.repositories.IHotelRepository;
import com.example.demo.repositories.IReservationRepository;
import com.example.demo.service.ReservationService;

import reactor.core.publisher.Flux;

@Component
public class Initializer implements ApplicationRunner
{
	// FIELDS
	private final IReservationRepository reservationRepository;
	private final IHotelRepository hotelRepository;
	private final ReservationService reservationService;
	
	private List<Hotel> commonlyUsedHotels = new ArrayList<Hotel>();
	
	
	// CONSTRUCTORS
	public Initializer(IReservationRepository reservationRepository, ReservationService reservationService, IHotelRepository hotelRepository)
	{
		super();
		this.reservationRepository = reservationRepository;
		this.hotelRepository = hotelRepository;
		this.reservationService = reservationService;
		
		initCommonlyUsedHotels();
	}
	
	
	
	
	// MAIN
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		
		//@formatter:off
		//init Mongo DB with sample hotels
		Flux.fromIterable(commonlyUsedHotels)			
				.flatMap(hotel -> hotelRepository.save(hotel))
				.blockLast(); //have to block it because findSuitableLodgings() needs the complete list of available hotels; it's a small hotel list for testing purposes anyway
		
		//init Mongo DB with sample reservations
		Flux.just(new ReservationDTO("Prof.", "Alexander", "Kalinowski", LocalDate.of(2022, 4, 1), LocalDate.of(2022, 8, 1)),
							new ReservationDTO("Dr.", "Peter L.", "Einstein", LocalDate.of(2022, 3, 23), LocalDate.of(2022, 8, 1)),
							new ReservationDTO("Dr.", "Victor", "Frankenstein", LocalDate.of(2022, 9, 29), LocalDate.of(2023, 3, 1)))	
				.flatMap(reservationDTO-> reservationService.findSuitableLodgingsSynchronously(reservationDTO))
				.flatMap(reservation -> reservationRepository.save(reservation))
				.blockLast(); //we want a fully initialized DB at program start				
		//@formatter:on
	}
	
	
	
	
	// HELPERS
	private void initCommonlyUsedHotels()
	{
		commonlyUsedHotels.clear();
		commonlyUsedHotels.add(new Hotel(1, "Hilton", new Address("Foo Street", 73, 66666, "Bar")));
		commonlyUsedHotels.add(new Hotel(2, "Sheraton", new Address("Main Street", 4, 45127, "Essen")));
		commonlyUsedHotels.add(new Hotel(3, "Etap", new Address("1st Avenue", 1, 45127, "Essen")));
		commonlyUsedHotels.add(new Hotel(4, "4 Seasons", new Address("Kettwiger Straße", 13, 45127, "Essen")));
		commonlyUsedHotels.add(new Hotel(5, "Kaiserhof", new Address("Bahnhofsstraße", 7, 45127, "Essen")));
	}
}
