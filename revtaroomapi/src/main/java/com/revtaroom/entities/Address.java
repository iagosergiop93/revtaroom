package com.revtaroom.entities;

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
	
}
