package com.revtaroom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revtaroom.entities.House;
import com.revtaroom.entities.Room;
import com.revtaroom.exceptions.BadRequest;
import com.revtaroom.repositories.HouseRepository;
import com.revtaroom.repositories.RoomRepository;

@Service
public class RoomService {
	
	private RoomRepository roomRepo;
	private HouseRepository houseRepo;
	
	@Autowired
	public RoomService(RoomRepository roomRepo, HouseRepository houseRepo) {
		super();
		this.roomRepo = roomRepo;
		this.houseRepo = houseRepo;
	}
	
	public List<Room> getAllRooms() {
		return roomRepo.findAll();
	}
	
	public Room insertRoom(Room room, long userId) {
		
		long houseId = room.getHouseId();
		
		Optional<House> optHouse = houseRepo.findById(houseId);
		if(!optHouse.isPresent()) throw new BadRequest(400, "There is not a house registered for this room");
		
		House house = optHouse.get();
		if(house.getUserId() != userId) throw new BadRequest(400, "Unauthorized User");
		
		room = roomRepo.save(room);
		
		return room;
	}
	
}
