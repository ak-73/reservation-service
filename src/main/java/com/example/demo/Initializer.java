package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Address;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.repositories.IReservationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class Initializer implements ApplicationRunner
{
	private final IReservationRepository reservationRepository;
	
	public Initializer(IReservationRepository reservationRepository)
	{
		super();
		this.reservationRepository = reservationRepository;
	}

	
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		System.out.println("run()");
		
		Hotel h1 = new Hotel(1, "Hilton", new Address("Foo Street", 73, 66666, "Bar"));
		Hotel h2 = new Hotel(2, "Sheraton", new Address("Main Street", 4, 45127, "Essen"));
		Hotel h3 = new Hotel(3, "Etap", new Address("1st Avenue", 1, 45127, "Essen"));
		
		//@formatter:off
		Flux.just(new ReservationDTO(1, "Prof.", "Alexander", "Kalinowski", h1, 13, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 8, 1)),
							//yes, the data is not normalized; it's just a reactive spring test
							new ReservationDTO(2, "Dr.", "Peter L.", "Einstein", h2, 66, LocalDate.of(2022, 3, 23), LocalDate.of(2022, 8, 1)),
							new ReservationDTO(3, "Dr.", "Victor", "Frankenstein", h3, 100, LocalDate.of(2022, 9, 29), LocalDate.of(2023, 3, 1)))	
				//.doOnEach(data -> System.out.println("Data: " + data.get())) //useful for looking into a stream
				//.map(data->new Reservation(data.id, data.name, data.hotel, data.roomNo, data.checkInDate, data.checkOutDate))
				//let's try to make it without the original above map() call work:	
				//this should be asynchronous, unlike map(); not that it makes really a difference here but it's a nice test
				.flatMap(data-> Mono.just(new  Reservation(data.id, data.title, data.firstName, data.lastName, data.hotel, data.roomNo, data.checkInDate, data.checkOutDate)))
				.flatMap(reservation -> reservationRepository.save(reservation))
				.subscribe(reservation -> System.out.println(reservation));
		//@formatter:on
	}
}
