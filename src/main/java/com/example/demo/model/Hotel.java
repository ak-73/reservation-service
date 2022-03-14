package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Hotel
{
	// FIELDS
	@Id
	private int id;
	private String name;
	Address address;
	
	
	// CONSTRUCTORS
	public Hotel(int id, String name, Address address)
	{
		super();
		this.id = id;
		this.name = name;
		this.address = address;
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
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	@Override
	public String toString()
	{
		return "Hotel [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}
