package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Hotel;


public interface IHotelRepository extends ReactiveMongoRepository<Hotel, Integer>
{
	
}
