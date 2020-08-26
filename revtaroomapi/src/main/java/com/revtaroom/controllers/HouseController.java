package com.revtaroom.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revtaroom.aop.Secured;
import com.revtaroom.entities.Address;
import com.revtaroom.entities.House;
import com.revtaroom.services.HouseService;

@RestController
@RequestMapping("/houses")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/house")
	@Secured(allowedRoles = { "USER", "ADMIN" })
	public House insertHouse(HttpServletRequest req, HttpServletResponse res, @RequestBody House house) {
		
		house = houseService.insertHouse(house);
		
		Address addr = houseService.getAndSaveCoordinates(house.getAddress());
		
		if(addr != null) house.setAddress(addr);
		
		return house;
	}
	
	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
	@ExceptionHandler
	public String exceptionHandler(Exception e) {
		return e.getMessage();
	}
	
}
