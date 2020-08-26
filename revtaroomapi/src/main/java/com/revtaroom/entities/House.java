package com.revtaroom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "houses")
@SequenceGenerator(name = "id_pk", sequenceName = "house_seq", allocationSize = 1)
@Getter @Setter @NoArgsConstructor
public class House {
	
	@Id
	@Column(name = "house_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pk")
	private long id;
	
	@Column(name = "rent_price", nullable = true, precision = 2)
	private double rentPrice;
	
	@Column(name = "description", nullable = true, length = 255)
	private String description;
	
	@Column(name = "user_id", nullable = false)
	private long userId;
	
	@OneToOne()
	@JoinColumn(name = "addr_id", nullable = false)
	private Address address;
	
}
