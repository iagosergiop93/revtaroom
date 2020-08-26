package com.revtaroom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revtaroom.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	List<Address> findAll();
	
	Address findById(long id);
	
	Address findByLatitudeAndLongitude(String latitude, String longitude);
	
	Address save(Address Address);
	
	List<Address> save(List<Address> addresses);
	
	void flush();
	
	void deleteById(long id);
	
}
