package com.revtaroom.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revtaroom.aop.Secured;
import com.revtaroom.dtos.Principal;
import com.revtaroom.entities.House;
import com.revtaroom.exceptions.BadRequest;
import com.revtaroom.services.HouseService;

@RestController
@RequestMapping("/houses")
public class HouseController {
	
	private HouseService houseService;
	
	@Autowired
	public HouseController(HouseService houseService) {
		this.houseService = houseService;
	}


	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/house")
	@Secured(allowedRoles = { "USER", "ADMIN" })
	public House insertHouse(HttpServletRequest req, HttpServletResponse res, @RequestBody House house) throws Throwable {
		
		Principal principal = (Principal) req.getAttribute("principal");
		System.out.println(principal.toString());
		if(principal.getId() != house.getUserId()) throw new BadRequest(403, "Unhauthorized user");
		
		house = houseService.insertHouse(house);
		
		houseService.getAndSaveCoordinates(house.getAddress()); // async method
		
		return house;
	}
	
}
