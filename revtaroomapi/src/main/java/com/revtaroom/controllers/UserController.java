package com.revtaroom.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revtaroom.dtos.Principal;
import com.revtaroom.entities.User;
import com.revtaroom.services.ProfileService;
import com.revtaroom.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private ProfileService profileService;
	
	@Autowired
	public UserController(UserService userService, ProfileService profileService) {
		this.userService = userService;
		this.profileService = profileService;
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAll();
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/user")
	public Principal createUser(@RequestBody User user) {
		
		Principal principal = userService.createUser(user);
		
		if(principal != null) {
			profileService.insertNewProfile(principal); // Asynchronous call
		}
		
		return principal;
	}
	
}
