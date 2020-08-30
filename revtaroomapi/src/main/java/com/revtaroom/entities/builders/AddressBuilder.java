package com.revtaroom.entities.builders;

import com.revtaroom.entities.Address;

public class AddressBuilder {
	
	private Address addr;
	
	public AddressBuilder() {
		addr = new Address();
	}
	
	public AddressBuilder streetAddr1(String street1) {
		this.addr.setStreetAddr1(street1);
		return this;
	}
	
	public AddressBuilder streetAddr2(String street2) {
		this.addr.setStreetAddr2(street2);
		return this;
	}
	
	public AddressBuilder city(String city) {
		this.addr.setCity(city);
		return this;
	}
	
	public AddressBuilder state(String state) {
		this.addr.setState(state);
		return this;
	}
	
	public AddressBuilder zip(String zip) {
		this.addr.setZip(zip);
		return this;
	}
	
	public AddressBuilder country(String country) {
		this.addr.setCountry(country);
		return this;
	}
	
	public Address build() {
		return addr;
	}
}
