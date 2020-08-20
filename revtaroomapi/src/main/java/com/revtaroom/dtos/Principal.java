package com.revtaroom.dtos;

import com.revtaroom.entities.User;
import com.revtaroom.entities.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public class Principal {
	
	private long id;
	private String firstName;
	private String lastName;
	private String username;
	private UserRole role;
	
	public Principal(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.role = user.getRole();
	}
	
}
