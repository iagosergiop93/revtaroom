package com.revtaroom.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revtaroom.apis.opencage.Geometry;
import com.revtaroom.apis.opencage.OpenCageClient;
import com.revtaroom.entities.Address;
import com.revtaroom.entities.House;
import com.revtaroom.repositories.AddressRepository;
import com.revtaroom.repositories.HouseRepository;

@Service
public class HouseService {
	
	@Autowired
	private HouseRepository houseRepo;
	
	@Autowired
	private AddressRepository addrRepo;
	
	@Autowired
	private OpenCageClient occ;
	
	public House insertHouse(House house) throws RuntimeException {
		
		// Get and save address
		Address addr = house.getAddress();
		addr = addrRepo.save(addr);
		house.setAddress(addr);
		
		// Save house
		house = houseRepo.save(house);
		
		return house;
	}
	
	
	public Address getAndSaveCoordinates(Address addr) {
		
		try {
			
			Geometry coords = occ.getCoordinates(addr);
			
			addr.setLatitude(Double.toString(coords.lat));
			addr.setLongitude(Double.toString(coords.lng));
			
			addrRepo.save(addr);
			
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		return addr;
	}
	
}
