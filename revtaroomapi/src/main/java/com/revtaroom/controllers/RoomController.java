package com.revtaroom.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revtaroom.aop.Secured;
import com.revtaroom.dtos.Principal;
import com.revtaroom.entities.Room;
import com.revtaroom.exceptions.BadRequest;
import com.revtaroom.services.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
	
	private RoomService roomService;
	
	@Autowired
	public RoomController(RoomService roomService) {
		super();
		this.roomService = roomService;
	}


	@Secured(allowedRoles = { "USER", "DEV", "ADMIN"})
	@GetMapping()
	public List<Room> getAllRooms(HttpServletRequest req, HttpServletResponse res) {
		List<Room> rooms = roomService.getAllRooms();
		return rooms;
	}
	
	@Secured(allowedRoles = { "USER", "DEV", "ADMIN"})
	@PostMapping("/room")
	public Room insertRoom(HttpServletRequest req, HttpServletResponse res, @RequestBody Room room) {
		
		Principal principal = (Principal) req.getAttribute("principal");
		room = roomService.insertRoom(room, principal.getId());
		
		return room;
	}
	
}
