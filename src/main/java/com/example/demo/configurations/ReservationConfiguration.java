package com.example.demo.configurations;

import org.springframework.context.annotation.Bean;

//import com.example.demo.controller.ReservationRESTController;
//import com.example.demo.repositories.ReservationRepository;
import com.mongodb.reactivestreams.client.MongoDatabase;

public class ReservationConfiguration
{
	/*
	@Bean
	ReservationRESTController createReservationRESTController(ReservationRepository repository)
	{
		return new ReservationRESTController(repository);
	}
	
	@Bean
	ReservationRepository createReservationRepository(MongoDatabase database)
	{
		return new ReservationRepository(database);
	}*/
	
	@Bean
	MongoDatabase createDatabase()
	{
		return null;
	}
}
