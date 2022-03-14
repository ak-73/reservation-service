package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Reservation
{
	// FIELDS
	@Id
	private int id;
	private String title;
	private String firstName;
	private String lastName;
	private Hotel hotel;
	private int roomNo;
	LocalDate checkInDate;
	LocalDate checkOutDate;
	
	
	
	// CONSTRUCTORS
	public Reservation()
	{
		super();
	}
	
	
	public Reservation(int id, String title, String firstName, String lastName, Hotel hotel, int roomNo, LocalDate checkInDate, LocalDate checkOutDate)
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
	
	
	
	
	// BOILERPLATE
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public Hotel getHotel()
	{
		return hotel;
	}
	
	public void setHotel(Hotel hotel)
	{
		this.hotel = hotel;
	}
	
	public int getRoomNo()
	{
		return roomNo;
	}
	
	public void setRoomNo(int roomNo)
	{
		this.roomNo = roomNo;
	}
	
	public LocalDate getCheckInDate()
	{
		return checkInDate;
	}
	
	public void setCheckInDate(LocalDate checkInDate)
	{
		this.checkInDate = checkInDate;
	}
	
	public LocalDate getCheckOutDate()
	{
		return checkOutDate;
	}
	
	public void setCheckOutDate(LocalDate checkOutDate)
	{
		this.checkOutDate = checkOutDate;
	}
	
	
	@Override
	public String toString()
	{
		return "Reservation [id=" + id + ", title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", hotel=" + hotel + ", roomNo=" + roomNo
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + "]";
	}
	
	
	
	
	
	
	
	
	
}
