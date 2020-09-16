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
@Table(name = "rooms")
@SequenceGenerator(name = "id_pk", sequenceName = "rooms_seq", allocationSize = 1)
@Getter @Setter @NoArgsConstructor
public class Room {
	
	@Id
	@Column(name = "room_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pk")
	private long id;
	
	@Column(name = "house_fk", nullable = false)
	private long houseId;
	
	@Column(nullable = false)
	private double price;
	
	@Column(length = 200)
	private String description;
	
	@Column(updatable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
	private Timestamp _createdAt;
	
	@Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Timestamp _updatedAt;
}
