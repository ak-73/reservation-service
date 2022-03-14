package com.example.demo.repositories;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reservation;
//import com.mongodb.reactivestreams.client.MongoDatabase;

import reactor.core.publisher.Flux;

@Repository
public class ReservationRepository
{
	// FIELDS
	private final ReactiveMongoTemplate mongoTemplate;
	
	
	// CONSTRUCTORS
	public ReservationRepository(ReactiveMongoTemplate mongoTemplate)
	{
		super();
		this.mongoTemplate = mongoTemplate;
	}
	
	
	// MAIN METHODS
	public Flux<Reservation> retrieveAllReservations()
	{
		return mongoTemplate.findAll(Reservation.class);
	}
	
}
