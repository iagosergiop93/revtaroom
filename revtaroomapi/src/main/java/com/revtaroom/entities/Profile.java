package com.revtaroom.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@SequenceGenerator(name = "id_pk", sequenceName = "profile_seq", allocationSize = 1)
@Getter @Setter @NoArgsConstructor
public class Profile {
	
	@Id
	@Column(name = "profile_id")
	private long id;
	
	@Column(length = 300)
	private String about;
	
	@Column(length = 50)
	private String training;
	
	@Column(name = "joined_revature")
	private LocalDate joinedRevature;
	
	@Column(name = "user_id", nullable = false)
	private long userId;
	
}
