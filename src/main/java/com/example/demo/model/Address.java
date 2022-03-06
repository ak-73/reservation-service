package com.example.demo.model;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address
{
	String streetName;
	int streetNumber;
	int postalCode;
	String postalRegionName;
	String state;
	String country;
	
	public Address(String streetName, int streetNumber, int postalCode, String postalRegionName)
	{
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.postalRegionName = postalRegionName;
		state = "NRW";
		country = "Germany";
	}

	public String getStreetName()
	{
		return streetName;
	}

	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	public int getStreetNumber()
	{
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber)
	{
		this.streetNumber = streetNumber;
	}

	public int getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(int postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getPostalRegionName()
	{
		return postalRegionName;
	}

	public void setPostalRegionName(String postalRegionName)
	{
		this.postalRegionName = postalRegionName;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(country, postalCode, postalRegionName, state, streetName, streetNumber);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Address other = (Address) obj;
		return Objects.equals(country, other.country) && postalCode == other.postalCode && Objects.equals(postalRegionName, other.postalRegionName)
				&& Objects.equals(state, other.state) && Objects.equals(streetName, other.streetName) && streetNumber == other.streetNumber;
	}

	@Override
	public String toString()
	{
		return "Address [streetName=" + streetName + ", streetNumber=" + streetNumber + ", postalCode=" + postalCode + ", postalRegionName=" + postalRegionName
				+ ", state=" + state + ", country=" + country + "]";
	}
	
}
