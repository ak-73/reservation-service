package com.example.demo.dtos;

import java.time.LocalDate;

public class ReservationDTO
{
	// FIELDS
	String title;
	String firstName;
	String lastName;
	LocalDate checkInDate;
	LocalDate checkOutDate;
	
	
	// CONSTRUCTORS
	public ReservationDTO()
	{
		super();
	}
	
	public ReservationDTO(String title, String firstName, String lastName, LocalDate checkInDate, LocalDate checkOutDate)
	{
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	
	
	// BOILERPLATE
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
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
		return "ReservationDTO [title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", checkInDate=" + checkInDate + ", checkOutDate="
				+ checkOutDate + "]";
	}
	
	
}
