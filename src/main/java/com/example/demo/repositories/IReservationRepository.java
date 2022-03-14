package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Reservation;

public interface IReservationRepository extends ReactiveMongoRepository<Reservation, Integer>
{
	
}
