package com.revtaroom.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.revtaroom.apis.opencage.OpenCageClient;
import com.revtaroom.apis.opencage.models.Geometry;
import com.revtaroom.entities.Address;
import com.revtaroom.entities.House;
import com.revtaroom.repositories.AddressRepository;
import com.revtaroom.repositories.HouseRepository;

@Service
public class HouseService {
	
	private HouseRepository houseRepo;
	private AddressRepository addrRepo;
	private OpenCageClient openCageClient;
	
	@Autowired
	public HouseService(HouseRepository houseRepo, AddressRepository addrRepo, OpenCageClient openCageClient) {
		super();
		this.houseRepo = houseRepo;
		this.addrRepo = addrRepo;
		this.openCageClient = openCageClient;
	}
	
	public List<House> getHouses() throws RuntimeException {
		List<House> houses = houseRepo.findAll();
		
		return houses;
	}

	@Transactional
	public House insertHouse(House house) throws RuntimeException {
		
		// Check if User already has a house
		House savedHouse = houseRepo.findByUserId(house.getUserId());
		
		if(savedHouse != null) houseRepo.delete(savedHouse);
		
		// Get and save address
		Address addr = house.getAddress();
		addr = addrRepo.save(addr);
		house.setAddress(addr);
		
		// Save house
		house = houseRepo.save(house);
		
		return house;
	}
	
	@Transactional
	@Async
	public CompletableFuture<Address> getAndSaveCoordinates(Address addr) {
		
		try {
			
			Geometry coords = openCageClient.getCoordinates(addr);
			
			addr.setLatitude(Double.toString(coords.lat));
			addr.setLongitude(Double.toString(coords.lng));
			
			addrRepo.save(addr);
			
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		return CompletableFuture.completedFuture(addr);
	}
	
}
