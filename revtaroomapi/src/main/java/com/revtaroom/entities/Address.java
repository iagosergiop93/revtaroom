package com.revtaroom.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@SequenceGenerator(name = "id_pk", sequenceName = "addr_seq", allocationSize = 1)
@Getter @Setter @NoArgsConstructor
public class Address {
	
	@Id
	@Column(name = "addr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pk")
	private long id;
	
	@Column(name = "street_addr1", nullable = false)
	private String streetAddr1;
	
	@Column(name = "street_addr2", nullable = true)
	private String streetAddr2;
	
	@Column(name = "house_number", nullable = true)
	private String houseNumber;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "zip", nullable = false)
	private String zip;
	
	@Column(name = "country", nullable = true)
	private String country;
	
	@Column(name = "latitude", nullable = true)
	private String latitude;
	
	@Column(name = "longitude", nullable = true)
	private String longitude;
	
	@Column(updatable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
	private Timestamp _createdAt;
	
	@Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Timestamp _updatedAt;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetAddr1 == null) ? 0 : streetAddr1.hashCode());
		result = prime * result + ((streetAddr2 == null) ? 0 : streetAddr2.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddr1 == null) {
			if (other.streetAddr1 != null)
				return false;
		} else if (!streetAddr1.equals(other.streetAddr1))
			return false;
		if (streetAddr2 == null) {
			if (other.streetAddr2 != null)
				return false;
		} else if (!streetAddr2.equals(other.streetAddr2))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}
	
	
	
}
