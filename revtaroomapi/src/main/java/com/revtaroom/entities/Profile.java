package com.revtaroom.entities;

import java.sql.Timestamp;
import java.time.LocalDate;

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
@Table(name = "profiles")
@SequenceGenerator(name = "id_pk", sequenceName = "profiles_seq", allocationSize = 1)
@Getter @Setter @NoArgsConstructor
public class Profile {
	
	@Id
	@Column(name = "profile_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pk")
	private long id;
	
	@Column(length = 300)
	private String about;
	
	@Column(length = 50)
	private String training;
	
	@Column(name = "joined_revature")
	private LocalDate joinedRevature;
	
	@Column(name = "user_id", nullable = false)
	private long userId;
	
	@Column(updatable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
	private Timestamp _createdAt;
	
	@Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Timestamp _updatedAt;
	
}
