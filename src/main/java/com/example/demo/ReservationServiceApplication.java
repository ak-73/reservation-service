package com.example.demo;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.service.ReservationService;

//security config for the actuator starter
@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class })
public class ReservationServiceApplication
{
	
	public static void main(String[] args)
	{
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
	
	@Bean
	RouterFunction<ServerResponse> routeRequest(ReservationService reservationHandler)
	{
		return route().GET("/reservation/{id}", reservationHandler::retrieveReservationByID)
				// reminder: can expose further endpoints the same way here
				.build();
	}
}
