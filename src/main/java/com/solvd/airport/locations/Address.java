package com.solvd.airport.locations;

import com.solvd.airport.exceptions.InvalidGeographyException;

import java.util.Objects;
import java.util.Optional;

public class Address extends Location {
	private Street street;
	private String number;
	private String unit;
	private String zip;

	public Address(String number, Street street, String zip) {
		this(number, street, "", zip);
	}

	public Address(String number, Street street, String unit, String zip) {
		this.number = number;
		this.street = street;
		this.unit = unit;
		this.zip = zip;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(street, address.street) && Objects.equals(number, address.number)
				&& Objects.equals(unit, address.unit) && Objects.equals(zip, address.zip);
	}

	@Override
	public String toString() {
		return number + " " + street + (!Objects.equals(unit, "") ? "\n" + unit : "")
				+ "\n" + street.getCity() + ", " + street.getCity().getStateProvince().getAcronym()
				+ "\n" + zip;
	}

	public Street getStreet() throws InvalidGeographyException {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}
	
	public Optional<String> getUnit() {
		return Optional.ofNullable(unit);
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
