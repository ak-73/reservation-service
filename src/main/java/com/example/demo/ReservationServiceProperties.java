package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//only needed for Kafka
@ConfigurationProperties("reservation.dto.consumer")
@Component
public class ReservationServiceProperties
{
	// FIELDS
	private String topic = "reservation_dto_topic_default";
	
	
	// CONSTRUCTORS
	public ReservationServiceProperties()
	{
		super();
	}
	
	
	// BOILERPLATE
	public String getTopic()
	{
		return topic;
	}
	
	public void setTopic(String topic)
	{
		this.topic = topic;
	}
	
}
