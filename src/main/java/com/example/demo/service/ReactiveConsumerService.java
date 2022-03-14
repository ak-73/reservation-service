package com.example.demo.service;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ReservationDTO;
import com.example.demo.model.Reservation;
import com.example.demo.repositories.IReservationRepository;

import reactor.core.publisher.Flux;

@Service
public class ReactiveConsumerService implements CommandLineRunner
{
	// FIELDS
	Logger log = LoggerFactory.getLogger(ReactiveConsumerService.class);
	
	private final ReactiveKafkaConsumerTemplate<String, ReservationDTO> reactiveKafkaConsumerTemplate;
	private final ReservationService reservationService;
	private final IReservationRepository reservationRepository;
	
	
	// CONSTRUCTORS
	public ReactiveConsumerService(ReactiveKafkaConsumerTemplate<String, ReservationDTO> reactiveKafkaConsumerTemplate, ReservationService reservationService,
			IReservationRepository reservationRepository)
	{
		this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
		this.reservationService = reservationService;
		this.reservationRepository = reservationRepository;
	}
	
	
	
	// MAIN
	@Override
	public void run(String... args)
	{
		// we have to trigger consumption
		consumeReservationDTO().subscribe();
	}
	
	
	
	// HELPERS
	private Flux<Reservation> consumeReservationDTO()
	{
		return reactiveKafkaConsumerTemplate.receiveAutoAck()
				// .delayElements(Duration.ofSeconds(2L)) // BACKPRESSURE
				.doOnNext(consumerRecord -> log.info("received key={}, value={} from topic={}, offset={}", consumerRecord.key(), consumerRecord.value(),
						consumerRecord.topic(), consumerRecord.offset()))
				.map(ConsumerRecord::value).flatMap(reservationDTO -> reservationService.findSuitableLodgingsReactively(reservationDTO))
				.flatMap(reservation -> reservationRepository.save(reservation))
				.doOnNext(reservation -> log.info("successfully consumed {}={}", Reservation.class.getSimpleName(), reservation))
				.doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage()));
	}
	
	
	// TODO remove with the next commit
	/*
	 * private final Logger log =
	 * LoggerFactory.getLogger(ReactiveConsumerService.class); private final
	 * ReactiveKafkaProducerTemplate<String, ReservationDTO>
	 * reactiveKafkaProducerTemplate;
	 * 
	 * @Value(value = "${reservation.dto.consumer.topic}") private String topic;
	 * 
	 * public ReactiveConsumerService(ReactiveKafkaProducerTemplate<String,
	 * ReservationDTO> reactiveKafkaProducerTemplate) {
	 * this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate; }
	 * 
	 * public void send(ReservationDTO reservationDTO) {
	 * log.info("send to topic={}, {}={},", topic,
	 * ReservationDTO.class.getSimpleName(), reservationDTO);
	 * reactiveKafkaProducerTemplate.send(topic, reservationDTO)
	 * .doOnSuccess(senderResult -> log.info("sent {} offset : {}", reservationDTO,
	 * senderResult.recordMetadata().offset())) .subscribe(); }
	 */
}
