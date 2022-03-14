package com.example.demo.configurations;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

import com.example.demo.dtos.ReservationDTO;

import reactor.kafka.receiver.ReceiverOptions;

@Configuration
public class ReservationConfiguration
{
	@Bean
	public ReceiverOptions<String, ReservationDTO> createReceiverOptions(@Value(value = "${reservation.dto.consumer.topic}") String topic,
			KafkaProperties kafkaProperties)
	{
		ReceiverOptions<String, ReservationDTO> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
		return basicReceiverOptions.subscription(Collections.singletonList(topic));
	}
	
	@Bean
	public ReactiveKafkaConsumerTemplate<String, ReservationDTO> createReactiveKafkaConsumerTemplate(
			ReceiverOptions<String, ReservationDTO> kafkaReceiverOptions)
	{
		return new ReactiveKafkaConsumerTemplate<String, ReservationDTO>(kafkaReceiverOptions);
	}
}
