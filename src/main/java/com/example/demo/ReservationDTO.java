package com.example.demo;

import java.time.LocalDate;

import com.example.demo.model.Hotel;

class ReservationDTO
{
	int id;	
	String title;
	String firstName;
	String lastName;	
	Hotel hotel;
	int roomNo;
	LocalDate checkInDate;
	LocalDate checkOutDate;
	
	ReservationDTO(int id,String title,  String firstName, String lastName, Hotel hotel, int roomNo, LocalDate checkInDate, LocalDate checkOutDate)
	{
		super();
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hotel = hotel;
		this.roomNo = roomNo;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	@Override
	public String toString()
	{
		return "ReservationDTO [id=" + id + ", title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", hotel=" + hotel + ", roomNo="
				+ roomNo + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + "]";
	}		
}